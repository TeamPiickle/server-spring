package com.team.piickle.card.controller;

import com.team.piickle.card.dto.CardResponseDto;
import com.team.piickle.card.service.CardService;
import com.team.piickle.util.dto.DataResponseDto;
import com.team.piickle.util.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/cards")
@RestController
public class CardController {

    private final CardService cardService;
    private static final int BEST_PIICKLE_SIZE = 30;
    private final MessageSource messageSource;

    @GetMapping("/best")
    private ResponseEntity<ResponseDto> user() {
        List<CardResponseDto> bestCardList = cardService.getBestCardList(BEST_PIICKLE_SIZE);
        return new ResponseEntity<>(
                DataResponseDto.of(
                        bestCardList,
                        messageSource.getMessage("CARD.BEST.LIST.VIEW.SUCCESS", null, Locale.getDefault())),
                HttpStatus.OK);
    }
}
