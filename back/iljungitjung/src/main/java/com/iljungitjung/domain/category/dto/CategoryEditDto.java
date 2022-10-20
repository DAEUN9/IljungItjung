package com.iljungitjung.domain.category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class CategoryEditDto {

    private int category_id;

    private String category_name;

    private String time;

    private String color;
}
