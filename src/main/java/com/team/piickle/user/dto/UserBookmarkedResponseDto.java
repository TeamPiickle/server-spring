package com.team.piickle.user.dto;

import com.team.piickle.card.domain.Card;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserBookmarkedResponseDto {
    private String cardId;
    private String content;
    private List<String> tags;
    private List<String> filter;
    private boolean isBookmark;

    public static UserBookmarkedResponseDto from(Card card) {
        return UserBookmarkedResponseDto.builder()
                .cardId(card.getId())
                .content(card.getContent())
                .tags(card.getTags())
                .filter(card.getFilter())
                .isBookmark(true)
                .build();
    }
}
