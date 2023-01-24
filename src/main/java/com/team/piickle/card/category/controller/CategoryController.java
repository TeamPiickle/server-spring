package com.team.piickle.card.category.controller;

import com.team.piickle.card.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CategoryController {

    private final CategoryService categoryService;
}
