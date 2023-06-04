package com.team.piickle.card.service;

import com.team.piickle.bookmark.repository.BookmarkRepository;
import com.team.piickle.card.domain.Card;
import com.team.piickle.card.domain.CardMedley;
import com.team.piickle.card.dto.CardMedleyPreviewResponseDto;
import com.team.piickle.card.dto.CardMedleyResponseDto;
import com.team.piickle.card.dto.CardResponseDto;
import com.team.piickle.card.repository.CardMedleyRepository;
import com.team.piickle.card.repository.CardRepository;
import com.team.piickle.common.exception.GeneralException;
import com.team.piickle.user.domain.User;
import com.team.piickle.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CardMedleyService {

    private final CardMedleyRepository cardMedleyRepository;
    private final CardRepository cardRepository;
    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;
    public List<CardMedleyPreviewResponseDto> getPreviewById() {
        List<CardMedley> allMedley = cardMedleyRepository.findAll();

        return allMedley.stream()
                .map(cardMedley -> {
                    List<CardMedleyPreviewResponseDto.PreviewCard> previewCards = cardRepository.findAllByIdIn(cardMedley.getPreviewCards()).stream()
                            .map(value -> CardMedleyPreviewResponseDto.previewCardOf(value))
                            .collect(Collectors.toList());
                    return CardMedleyPreviewResponseDto.of(cardMedley, previewCards);
                }).collect(Collectors.toList());
    }

    public CardMedleyResponseDto getCardsById(String id, Optional<String> userEmail) {
        CardMedley cardMedley = cardMedleyRepository.findById(id)
                .orElseThrow(() -> new GeneralException("해당하는 아이디의 카드 메들리가 없습니다."));
        List<CardMedleyPreviewResponseDto.PreviewCard> previewCardList = cardMedley.getPreviewCards()
                .stream()
                .map(value -> {
                    Card card = cardRepository.findById(value)
                            .orElseThrow(() -> new GeneralException("해당하는 아이디의 카드를 찾을 수 없습니다."));
                    return CardMedleyPreviewResponseDto.previewCardOf(card);
                })
                .collect(Collectors.toList());
        List<CardResponseDto> cardResponseDtos = cardMedley.getCards()
                .stream()
                .map(card -> {
                    Card findCard = cardRepository.findById(card)
                            .orElseThrow(() -> new GeneralException("해당하는 아이디의 카드를 찾을 수 없습니다."));
                    boolean bookmarked = true;
                    if (!userEmail.isEmpty()) {
                        User user = userRepository.findByEmail(userEmail.get())
                                .orElseThrow(() -> new GeneralException("아이디에 해당하는 유저를 찾을 수 없습니다."));
                        if (bookmarkRepository.findByUserAndCard(new ObjectId(user.getId()), new ObjectId(findCard.getId())).isEmpty()) {
                            bookmarked = false;
                        }
                    }
                    return CardResponseDto.of(findCard, bookmarked);
                }).collect(Collectors.toList());
        Collections.shuffle(cardResponseDtos);
        return new CardMedleyResponseDto(CardMedleyPreviewResponseDto.of(cardMedley, previewCardList), cardResponseDtos);
    }
}
