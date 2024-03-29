package com.team.piickle.category.service;

import com.team.piickle.bookmark.repository.BookmarkRepository;
import com.team.piickle.card.domain.Card;
import com.team.piickle.card.dto.CardResponseDto;
import com.team.piickle.card.repository.CardRepository;
import com.team.piickle.category.domain.Category;
import com.team.piickle.category.dto.CategoryResponseDto;
import com.team.piickle.category.dto.CategoryWithCardsResponseDto;
import com.team.piickle.category.repository.CategoryRepository;
import com.team.piickle.common.exception.GeneralException;
import com.team.piickle.user.domain.User;
import com.team.piickle.user.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private static final String NO_RESULT = "";
    private static final int CARD_SIZE_PER_REQUEST = 30;
    private final CategoryRepository categoryRepository;
    private final CardRepository cardRepository;
    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;

    public List<CategoryResponseDto> getCategories() {
        return categoryRepository.findByOrderByOrder().stream()
                .map(value -> CategoryResponseDto.from(value))
                .collect(Collectors.toList());
    }

    public CategoryWithCardsResponseDto getCardsWithIsBookmark(
            Optional<String> userEmail, String categoryId) {
        Category category =
                categoryRepository
                        .findById(categoryId)
                        .orElseThrow(() -> new GeneralException("해당 id의 카테고리가 없습니다."));
        Optional<User> user = userRepository.findByEmail(userEmail.orElse(NO_RESULT));

        List<Card> cards = cardRepository.findAllByIdIn(category.getCardIdList());
        List<Card> randomCards =
                getRandomUniqueNumberInRange(cards.size(), CARD_SIZE_PER_REQUEST).stream()
                        .map(value -> cards.get(value))
                        .collect(Collectors.toList());
        List<CardResponseDto> cardList =
                randomCards.stream()
                        .map(
                                value -> {
                                    String userId = NO_RESULT;
                                    if (!user.isEmpty()) {
                                        userId = user.get().getId();
                                    }
                                    if (!bookmarkRepository
                                            .findByUserAndCard(new ObjectId(userId), new ObjectId(value.getId()))
                                            .isEmpty()) {
                                        return CardResponseDto.from(value).changeIsBookmarked(true);
                                    }
                                    return CardResponseDto.from(value);
                                })
                        .collect(Collectors.toList());
        return CategoryWithCardsResponseDto.of(categoryId, category.getTitle(), cardList);
    }

    public List<CardResponseDto> getCardsBySearch(List<String> search, Optional<String> userEmail) {
        List<Card> cardList = cardRepository.findByFilter(search);
        List<Card> randomCardList =
                getRandomUniqueNumberInRange(cardList.size(), CARD_SIZE_PER_REQUEST).stream()
                        .map(value -> cardList.get(value))
                        .collect(Collectors.toList());
        List<CardResponseDto> cardResponseDtoList =
                randomCardList.stream()
                        .map(
                                value -> {
                                    User user =
                                            userRepository
                                                    .findByEmail(
                                                            userEmail.orElseThrow(() -> new GeneralException("토큰이 유효하지 않습니다.")))
                                                    .orElseThrow(() -> new GeneralException("아이디에 해당하는 유저를 찾을 수 없습니다."));
                                    if (!bookmarkRepository
                                            .findByUserAndCard(new ObjectId(user.getId()), new ObjectId(value.getId()))
                                            .isEmpty()) {
                                        return CardResponseDto.from(value).changeIsBookmarked(true);
                                    }
                                    return CardResponseDto.from(value);
                                })
                        .collect(Collectors.toList());
        return cardResponseDtoList;
    }

    private List<Integer> getRandomUniqueNumberInRange(int size, int cardSizePerRequest) {
        List<Integer> result = new ArrayList<>();
        int resultSize = Math.min(size, cardSizePerRequest);
        while (result.size() < resultSize) {
            int randomNumberInRange = (int) Math.floor(Math.random() * size);
            if (!result.contains(randomNumberInRange)) {
                result.add(randomNumberInRange);
            }
        }
        return result;
    }
}
