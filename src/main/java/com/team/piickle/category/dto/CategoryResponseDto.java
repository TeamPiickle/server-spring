package com.team.piickle.category.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.team.piickle.category.domain.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponseDto {

    @JsonProperty("_id")
    private String id;
    private String title;
    private String content;
    private String unicode;
    private String gradation;

    public CategoryResponseDto(Category category) {
        this.id = category.getId();
        this.title = category.getTitle();
        this.content = category.getContent();
        this.unicode = category.getUnicode();
        this.gradation = category.getGradation();
    }
}
