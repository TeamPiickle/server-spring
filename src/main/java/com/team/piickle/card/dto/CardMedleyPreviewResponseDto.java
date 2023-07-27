package com.team.piickle.card.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.team.piickle.card.domain.Card;
import com.team.piickle.card.domain.CardMedley;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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

    public static CardMedleyPreviewResponseDto.PreviewCard previewCardOf(Card card) {
        return PreviewCard.builder().id(card.getId()).content(card.getContent()).build();
    }

    public static CardMedleyPreviewResponseDto of(
            CardMedley cardMedley, List<PreviewCard> previewCards) {
        return CardMedleyPreviewResponseDto.builder()
                .id(cardMedley.getId())
                .coverTitle(cardMedley.getCoverTitle())
                .title(cardMedley.getTitle())
                .sticker(cardMedley.getSticker())
                .description(cardMedley.getDescription())
                .previewCards(previewCards)
                .build();
    }
}
