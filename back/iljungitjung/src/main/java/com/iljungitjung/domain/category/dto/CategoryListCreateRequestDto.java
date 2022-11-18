package com.iljungitjung.domain.category.dto;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryListCreateRequestDto {

    private List<CategoryCreateDto> categoryList;
}
