package com.iljungitjung.domain.category.controller;

import com.iljungitjung.domain.category.dto.CategoryCreateRequestDto;
import com.iljungitjung.domain.category.dto.CategoryEditRequestDto;
import com.iljungitjung.domain.category.service.CategoryService;
import com.iljungitjung.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CommonResponse> createCategory(
            @RequestBody @Valid CategoryCreateRequestDto requestDto
            , HttpSession httpSession
    ) {
        return new ResponseEntity<>(CommonResponse.getSuccessResponse(categoryService.addCategory(requestDto, httpSession)), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CommonResponse> updateCategory(
            @RequestBody @Valid CategoryEditRequestDto requestDto
            , HttpSession httpSession
    ) {
        return new ResponseEntity<>(CommonResponse.getSuccessResponse(categoryService.updateCategory(requestDto, httpSession)), HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<CommonResponse> deleteCategory(
            @PathVariable("categoryId") Long categoryId
            , HttpSession httpSession
    ) {
        return new ResponseEntity<>(CommonResponse.getSuccessResponse(categoryService.deleteCategory(categoryId, httpSession)), HttpStatus.OK);
    }


}
