package com.iljungitjung.domain.category.controller;

import com.iljungitjung.domain.category.dto.CategoryCreateRequestDto;
import com.iljungitjung.domain.category.dto.CategoryEditRequestDto;
import com.iljungitjung.domain.category.service.CategoryService;
import com.iljungitjung.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(value = "/categories")
public class CategoryController {

    private final CategoryService categoryService;

    // user생기면 session으로 수정
    @PostMapping
    public ResponseEntity<?> createCategory(
            @RequestBody CategoryCreateRequestDto requestDto
    ) {
        return new ResponseEntity<>(CommonResponse.getSuccessResponse(categoryService.addCategory(requestDto)), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateCategory(
            @RequestBody CategoryEditRequestDto requestDto
    ) {
        return new ResponseEntity<>(CommonResponse.getSuccessResponse(categoryService.updateCategory(requestDto)), HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(
            @PathVariable("categoryId") Long categoryId
    ) {
        return new ResponseEntity<>(CommonResponse.getSuccessResponse(categoryService.deleteCategory(categoryId)), HttpStatus.OK);
    }


}
