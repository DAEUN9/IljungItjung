package com.iljungitjung.domain.category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class CategoryEditDto {

    private int categoryId;

    private String categoryName;

    private String time;

    private String color;
}
