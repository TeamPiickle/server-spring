package com.team.piickle.card.service;

import com.team.piickle.bookmark.repository.BookmarkRepository;
import com.team.piickle.card.domain.Card;
import com.team.piickle.card.dto.CardResponseDto;
import com.team.piickle.card.repository.CardRepository;
import com.team.piickle.user.domain.User;
import com.team.piickle.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CardService {

    private final CardRepository cardRepository;
    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;

    @Transactional
    public List<CardResponseDto> getBestCardList(Long userId) {
        List<Long> bestCardsId = new ArrayList<>();
                //bookmarkRepository.findBestCardsId();
        User user = userRepository.findById("userId")
                .orElseGet(null);
        List<Card> cardList = new ArrayList<>();
                //cardRepository.findAllByIdList(new ArrayList<>());
        List<CardResponseDto> cardResponseDtoList = cardList.stream().map(card -> new CardResponseDto(card))
                .collect(Collectors.toList());
        if (!user.equals(null)) {
            cardResponseDtoList.stream()
//                    .map(card -> card.changeIsBookmarked(null)
                    .collect(Collectors.toList());
        }
        return cardResponseDtoList;
    }
}
