package com.team.piickle.category.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.team.piickle.card.dto.CardResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CategoryWithCardsResponseDto {
    @JsonProperty("_id")
    private String id;
    private String title;
    private List<CardResponseDto> cardList;

    public static CategoryWithCardsResponseDto of(String id, String title, List<CardResponseDto> cardList) {
        return CategoryWithCardsResponseDto.builder()
                .id(id)
                .title(title)
                .cardList(cardList)
                .build();
    }

}
