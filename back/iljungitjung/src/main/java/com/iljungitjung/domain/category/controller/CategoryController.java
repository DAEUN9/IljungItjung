package com.iljungitjung.domain.category.controller;

import com.iljungitjung.domain.category.dto.CategoryListCreateRequestDto;
import com.iljungitjung.domain.category.service.CategoryService;
import com.iljungitjung.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/categories")
@Validated
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CommonResponse> createCategory(
            @RequestBody @Valid CategoryListCreateRequestDto requestDto
            , HttpSession httpSession
    ) {
        return new ResponseEntity<>(CommonResponse.getSuccessResponse(categoryService.addCategory(requestDto, httpSession)), HttpStatus.OK);
    }



}
