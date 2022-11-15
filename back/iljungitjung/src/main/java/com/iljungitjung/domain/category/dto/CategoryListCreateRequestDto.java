package com.iljungitjung.domain.category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CategoryListCreateRequestDto {
    private List<CategoryCreateRequestDto> categoryList;

}
