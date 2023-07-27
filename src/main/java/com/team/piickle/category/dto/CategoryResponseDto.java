package com.team.piickle.category.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.team.piickle.category.domain.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoryResponseDto {

    @JsonProperty("_id")
    private String id;

    private String title;
    private String content;
    private String unicode;
    private String gradation;

    public static CategoryResponseDto from(Category category) {
        return CategoryResponseDto.builder()
                .id(category.getId())
                .title(category.getTitle())
                .content(category.getContent())
                .unicode(category.getUnicode())
                .gradation(category.getGradation())
                .build();
    }
}
