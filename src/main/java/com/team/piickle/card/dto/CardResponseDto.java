package com.team.piickle.card.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.team.piickle.card.domain.Card;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardResponseDto {
    @JsonProperty("_id")
    private String cardId;

    private String content;
    private List<String> categories;
    private List<String> filters;
    private List<String> tags;

    @JsonProperty("isBookmarked")
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

    public static CardResponseDto from(Card card) {
        return CardResponseDto.builder()
                .cardId(card.getId())
                .content(card.getContent())
                .categories(card.getCategory())
                .filters(card.getFilter())
                .tags(card.getTags())
                .isBookmarked(false)
                .build();
    }

    public static CardResponseDto of(Card card, boolean isBookmarked) {
        return CardResponseDto.builder()
                .cardId(card.getId())
                .content(card.getContent())
                .tags(card.getTags())
                .categories(card.getCategory())
                .filters(card.getFilter())
                .isBookmarked(isBookmarked)
                .build();
    }
}
