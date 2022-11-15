package com.iljungitjung.domain.category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryListCreateRequestDto {
    private List<CategoryCreateDto> categoryList;

}
