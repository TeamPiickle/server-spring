package com.team.piickle.category.controller;

import com.team.piickle.auth.jwt.TokenProvider;
import com.team.piickle.card.dto.CardResponseDto;
import com.team.piickle.category.dto.CategoryResponseDto;
import com.team.piickle.category.dto.CategoryWithCardsResponseDto;
import com.team.piickle.category.service.CategoryService;
import com.team.piickle.util.dto.DataResponseDto;
import com.team.piickle.util.dto.ResponseDto;
import java.util.List;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/categories")
@RestController
public class CategoryController {

    private final MessageSource messageSource;
    private final CategoryService categoryService;
    private final TokenProvider tokenProvider;

    @GetMapping()
    private ResponseEntity<ResponseDto> category() {
        List<CategoryResponseDto> categories = categoryService.getCategories();
        return new ResponseEntity<>(
                DataResponseDto.of(
                        categories,
                        messageSource.getMessage("USER.PROFILE.VIEW.SUCCESS", null, Locale.getDefault())),
                HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    private ResponseEntity<ResponseDto> cards(@PathVariable("categoryId") String categoryId) {
        CategoryWithCardsResponseDto categories =
                categoryService.getCardsWithIsBookmark(tokenProvider.getUserId(), categoryId);
        return new ResponseEntity<>(
                DataResponseDto.of(
                        categories,
                        messageSource.getMessage("USER.PROFILE.VIEW.SUCCESS", null, Locale.getDefault())),
                HttpStatus.OK);
    }

    @GetMapping("/cards")
    private ResponseEntity<ResponseDto> getCardsBySearch(
            @RequestParam(value = "search", required = false) List<String> search) {
        List<CardResponseDto> data =
                categoryService.getCardsBySearch(search, tokenProvider.getUserId());
        return new ResponseEntity<>(
                DataResponseDto.of(
                        data, messageSource.getMessage("USER.PROFILE.VIEW.SUCCESS", null, Locale.getDefault())),
                HttpStatus.OK);
    }
}
