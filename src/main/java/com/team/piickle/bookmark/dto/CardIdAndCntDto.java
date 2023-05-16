package com.team.piickle.bookmark.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardIdAndCntDto {
    @JsonProperty("_id")
    private String id;
    private int count;
}
