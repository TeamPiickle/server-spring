package com.team.piickle.card.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CardMedleyPreviewResponseDto {

    @JsonProperty("_id")
    private String id;
    private String coverTitle;
    private String title;
    private String sticker;
    private String description;
    private List<PreviewCard> previewCards;

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class PreviewCard {
        @JsonProperty("_id")
        private String id;
        private String content;
    }
}
