package com.team.piickle.card.dto;

import com.team.piickle.card.category.domain.Category;
import com.team.piickle.card.domain.Card;
import com.team.piickle.card.filter.CardFilter;
import com.team.piickle.card.tag.CardTag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CardResponseDto {
    private Long cardId;
    private String content;
    private List<Category> categories;
    private List<CardFilter> filters;
    private List<CardTag> tags;

    private boolean isBookmarked;

    public CardResponseDto changeIsBookmarked(boolean bookmarked) {
        if (bookmarked) {
            isBookmarked = true;
        }
        if (!bookmarked) {
            isBookmarked = false;
        }
        return this;
    }
    public CardResponseDto(Card card) {
        this.cardId = card.getId();
        this.content = card.getContent();
        this.filters = card.getFilters();
        this.tags = card.getTags();
    }
}
