package com.team.piickle.card.dto;

import com.team.piickle.card.category.domain.Category;
import com.team.piickle.card.domain.Card;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CardResponseDto {
    private String cardId;
    private String content;
    private List<Category> categories;
    private List<String> filters;
    private List<String> tags;
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
        this.filters = card.getFilter();
        this.tags = card.getTags();
    }
}
