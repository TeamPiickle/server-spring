package com.team.piickle.card.controller;

import com.team.piickle.auth.jwt.TokenProvider;
import com.team.piickle.card.dto.CardMedleyPreviewResponseDto;
import com.team.piickle.card.dto.CardMedleyResponseDto;
import com.team.piickle.card.service.CardMedleyService;
import com.team.piickle.util.dto.DataResponseDto;
import com.team.piickle.util.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/medley")
@RestController
public class CardMedleyController {

    private final CardMedleyService cardMedleyService;
    private final TokenProvider tokenProvider;

    @GetMapping()
    public ResponseEntity<ResponseDto> getAllMedleyPreview() {
        List<CardMedleyPreviewResponseDto> data = cardMedleyService.getPreviewById();
        return new ResponseEntity<>(
                DataResponseDto.of(
                        data,
                        "모든 카드 메들리 프리뷰 조회 성공"),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getCardMedleyById(@PathVariable(value = "id") String id) {
        CardMedleyResponseDto data = cardMedleyService.getCardsById(id, tokenProvider.getUserId());
        return new ResponseEntity<>(
                DataResponseDto.of(
                        data,
                        "모든 카드 메들리 프리뷰 조회 성공"),
                HttpStatus.OK);
    }
}
