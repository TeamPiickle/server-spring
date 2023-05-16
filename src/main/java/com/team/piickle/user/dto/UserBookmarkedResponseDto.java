package com.team.piickle.user.dto;

import com.team.piickle.card.domain.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserBookmarkedResponseDto {
    private String cardId;
    private String content;
    private List<String> tags;
    private List<String> filter;
    private boolean isBookmark = true;

    public UserBookmarkedResponseDto(Card card) {
        this.cardId = card.getId();
        this.content = card.getContent();
        this.tags = card.getTags();
        this.filter = card.getFilter();
        this.isBookmark = true;
    }
}
