package com.iljungitjung.domain.category.controller;

import com.iljungitjung.domain.category.dto.CategoryCreateRequestDto;
import com.iljungitjung.domain.category.dto.CategoryEditRequestDto;
import com.iljungitjung.domain.category.service.CategoryService;
import com.iljungitjung.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/categories")
@Validated
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
    public void deleteCategory(
            @Pattern(regexp = "^[0-9]+$", message = "categoryId는 숫자만 입력가능합니다.") @PathVariable("categoryId") Long categoryId
            , HttpSession httpSession
    ) {
        categoryService.deleteCategory(categoryId, httpSession);
        }


}
