package com.team.piickle.card.service;

import com.team.piickle.bookmark.dto.CardIdAndCntDto;
import com.team.piickle.bookmark.repository.BookmarkRepository;
import com.team.piickle.card.domain.Card;
import com.team.piickle.card.dto.CardResponseDto;
import com.team.piickle.card.repository.CardRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CardService {

    private final CardRepository cardRepository;
    private final BookmarkRepository bookmarkRepository;

    @Transactional
    public List<CardResponseDto> getBestCardList(int size) {
        List<CardIdAndCntDto> cards = findBestCardsLimit(size);
        if (cards.size() < size) {
            cards.addAll(findExtraCardsExceptFor(cards, size));
        }
        List<Card> findCards =
                cardRepository.findAllByIdIn(
                        cards.stream().map(value -> value.getId()).collect(Collectors.toList()));
        return findCards.stream()
                .map(value -> CardResponseDto.from(value))
                .collect(Collectors.toList());
    }

    private List<CardIdAndCntDto> findExtraCardsExceptFor(List<CardIdAndCntDto> cards, int size) {
        return cardRepository.findByNIdAndSortAndExtraLimit(
                cards.stream().map(value -> value.getId()).collect(Collectors.toList()),
                size - cards.size());
    }

    private List<CardIdAndCntDto> findBestCardsLimit(int size) {
        List<CardIdAndCntDto> cardIdAndCntDtos = bookmarkRepository.groupByCardAndSort(size);
        return cardIdAndCntDtos;
    }
}
