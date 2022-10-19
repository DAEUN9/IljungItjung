package com.iljungitjung.domain.category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryCreateDto {

    private String category_name;

    private String time;

    private String color;

}
