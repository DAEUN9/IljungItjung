package com.iljungitjung.domain.category.controller;

import com.iljungitjung.domain.category.dto.CategoryCreateDto;
import com.iljungitjung.domain.category.dto.CategoryEditDto;
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
    public ResponseEntity<CommonResponse> createCategory(
            @RequestBody CategoryCreateDto requestDto
    ) {
        categoryService.addCategory(requestDto);
        return new ResponseEntity<>(CommonResponse.getSuccessResponse(""), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CommonResponse> updateCategory(
            @RequestBody CategoryEditDto requestDto
    ) {
        categoryService.updateCategory(requestDto);
        return new ResponseEntity<>(CommonResponse.getSuccessResponse(""), HttpStatus.OK);
    }

    @DeleteMapping("/{category_id}")
    public ResponseEntity<CommonResponse> deleteCategory(
            @PathVariable("category_id") int categoryId
    ) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(CommonResponse.getSuccessResponse(""), HttpStatus.OK);
    }


}
