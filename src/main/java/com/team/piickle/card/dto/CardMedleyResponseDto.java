package com.team.piickle.card.dto;

import java.util.List;

public class CardMedleyResponseDto extends CardMedleyPreviewResponseDto{
    private List<CardResponseDto> cards;
    public CardMedleyResponseDto(CardMedleyPreviewResponseDto cardMedleyPreviewResponseDto, List<CardResponseDto> cards) {
        super(cardMedleyPreviewResponseDto.getId(), cardMedleyPreviewResponseDto.getCoverTitle(), cardMedleyPreviewResponseDto.getTitle(), cardMedleyPreviewResponseDto.getSticker(), cardMedleyPreviewResponseDto.getDescription(), cardMedleyPreviewResponseDto.getPreviewCards());
        this.cards = cards;
    }

}
