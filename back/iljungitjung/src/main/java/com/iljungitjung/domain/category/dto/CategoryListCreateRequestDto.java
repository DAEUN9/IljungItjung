package com.iljungitjung.domain.category.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class CategoryListCreateRequestDto {
    private List<CategoryCreateRequestDto> categoryList;

}
