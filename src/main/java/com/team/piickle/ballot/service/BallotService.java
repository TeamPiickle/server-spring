package com.team.piickle.ballot.service;

import com.team.piickle.auth.jwt.TokenProvider;
import com.team.piickle.ballot.domain.BallotItem;
import com.team.piickle.ballot.domain.BallotResult;
import com.team.piickle.ballot.domain.BallotTopic;
import com.team.piickle.ballot.dto.BallotRequestDto;
import com.team.piickle.ballot.dto.BallotStatusDto;
import com.team.piickle.ballot.dto.BallotTopicResponseDto;
import com.team.piickle.ballot.repository.BallotItemRepository;
import com.team.piickle.ballot.repository.BallotResultRepository;
import com.team.piickle.ballot.repository.BallotTopicRepository;
import com.team.piickle.common.exception.GeneralException;
import com.team.piickle.user.repository.UserRepository;
import com.team.piickle.util.calculation.Calculation;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class BallotService {

    private final BallotItemRepository ballotItemRepository;
    private final BallotResultRepository ballotResultRepository;
    private final BallotTopicRepository ballotTopicRepository;
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    @Transactional
    public void ballot(BallotRequestDto ballotRequestDto) {
        validBallotTopic(ballotRequestDto.getBallotTopicId());
        BallotResult ballotResult = ballotResultRepository.findByBallotTopicIdAndUserIdOrGuestId(ballotRequestDto.getBallotTopicId(), ballotRequestDto.getUserId(), ballotRequestDto.getGuestId());
        validBallotResult(ballotResult, ballotRequestDto.getBallotItemId());
        validBallotItem(ballotRequestDto.getBallotItemId());
        ballotResultRepository.save(BallotResult.builder()
                .ballotItemId(String.valueOf(ballotRequestDto.getBallotItemId()))
                .ballotTopicId(String.valueOf(ballotRequestDto.getBallotTopicId()))
                .guestId(ballotRequestDto.getGuestId())
                .userId(String.valueOf(ballotRequestDto.getUserId())).build());
    }

    @Transactional
    public List<BallotTopicResponseDto> getBallotTopicList(String userEmail) {
        if (userEmail == null) {
            List<BallotTopic> ballotTopicList = ballotTopicRepository.findAllByOrderByOrder(PageRequest.of(0, 4));
            return ballotTopicList.stream()
                    .map(ballotTopic -> BallotTopicResponseDto.from(ballotTopic))
                    .collect(Collectors.toList());
        }
        String userId = "";
        if (!userRepository.findByEmail(userEmail).isEmpty()) {
            userId = userRepository.findByEmail(userEmail).get().getId();
        }
        List<BallotResult> completedIds = ballotResultRepository.findByUserId(userId);
        return getRandomBallotTopics(completedIds);
    }

    @Transactional
    public BallotStatusDto getBallotStatusAndUserSelect(String ballotTopicId, String userEmail) {
        BallotTopic ballotTopic = ballotTopicRepository.findById(ballotTopicId)
                .orElseThrow(() -> new GeneralException("올바르지 않은 투표 주제 id 입니다."));
        List<BallotItem> ballotItems = ballotItemRepository.findByBallotTopicId(new ObjectId(ballotTopicId));
        String userId = "";
        if (!userRepository.findByEmail(userEmail).isEmpty()) {
            userId = userRepository.findByEmail(userEmail).get().getId();
        }
        String userSelectId = "";
        if (!ballotResultRepository.findByBallotTopicIdAndUserId(new ObjectId(ballotTopicId), new ObjectId(userId)).isEmpty()) {
            userSelectId = ballotResultRepository.findByBallotTopicIdAndUserId(new ObjectId(ballotTopicId), new ObjectId(userId))
                    .get()
                    .getId();
        }
        int ballotCount = ballotResultRepository.findByBallotTopicId(ballotTopicId).size();
        String finalUserSelectId = userSelectId;
        List<BallotStatusDto.BallotItem> ballotItemWithStatusList = ballotItems.stream()
                .map(value -> {
                    int status = 0;
                    if (!finalUserSelectId.equals("")) {
                        status = Calculation.getRatio(ballotCount, ballotResultRepository.findByBallotItemId(value.getId()).size());
                    }
                    return BallotStatusDto.ballotItemOf(value.getId(), status, value.getName());
                }).collect(Collectors.toList());
        return BallotStatusDto.builder()
                .ballotItems(ballotItemWithStatusList)
                .ballotTopic(BallotStatusDto.ballotTopicOf(ballotTopicId, ballotTopic.getTopic()))
                .userSelect(finalUserSelectId)
                .beforeTopicId(getLargestOrderTopicIdLessThan(ballotTopic.getOrder()))
                .nextTopicId(getSmallestOrderTopicIdGreaterThan(ballotTopic.getOrder()))
                .build();
    }

    private String getSmallestOrderTopicIdGreaterThan(long order) {
        List<BallotTopic> findBallotTopic = ballotTopicRepository.findOneSmallerOrder(order);
        if (findBallotTopic.size() == 0) {
            return "";
        }
        return findBallotTopic.get(0).getId();
    }

    private String getLargestOrderTopicIdLessThan(long order) {
        List<BallotTopic> findBallotTopic = ballotTopicRepository.findOneBiggerOrder(order);
        if (findBallotTopic.size() == 0) {
            return "";
        }
        return findBallotTopic.get(0).getId();
    }

    private List<BallotTopicResponseDto> getRandomBallotTopics(List<BallotResult> completedIds) {
        List<BallotTopicResponseDto> randomBallotTopicsExcludeCompleted = ballotTopicRepository.findByIdNinOrderByOrder(completedIds.stream()
                        .map(ballotTopic -> ballotTopic.getBallotTopicId())
                        .collect(Collectors.toList()), PageRequest.of(0, 4))
                .stream()
                .map(ballotTopic -> BallotTopicResponseDto.from(ballotTopic))
                .collect(Collectors.toList());
        if (randomBallotTopicsExcludeCompleted.size() < 4) {
            List<BallotTopicResponseDto> randomBallotTopicsCompleted = ballotTopicRepository.findByIdInOrderByOrder(completedIds.stream()
                            .map(ballotTopic -> ballotTopic.getBallotTopicId())
                            .collect(Collectors.toList()), PageRequest.of(0, 4 - randomBallotTopicsExcludeCompleted.size()))
                    .stream()
                    .map(ballotTopic -> BallotTopicResponseDto.from(ballotTopic))
                    .collect(Collectors.toList());
            return Stream.concat(randomBallotTopicsCompleted.stream(), randomBallotTopicsExcludeCompleted.stream())
                    .collect(Collectors.toList());
        }
        return randomBallotTopicsExcludeCompleted;
    }

    private void validBallotTopic(String ballotTopicId) {
        ballotTopicRepository.findById(String.valueOf(ballotTopicId))
                .orElseThrow(() -> new GeneralException("올바르지 않은 투표 주제 id 입니다."));
    }

    private void validBallotItem(String ballotItemId) {
        ballotItemRepository.findById(ballotItemId)
                .orElseThrow(() -> new GeneralException("올바르지 않은 투표 항목 id 입니다."));
    }

    private void validBallotResult(BallotResult ballotResult, String ballotItemId) {
        if (ballotResult != null) {
            if (ballotResult.getBallotItemId().equals(ballotItemId)) {
                throw new GeneralException("이미 투표한 항목입니다.");
            }
            ballotResultRepository.save(ballotResult.update(String.valueOf(ballotItemId)));
            return;
        }
    }
}
