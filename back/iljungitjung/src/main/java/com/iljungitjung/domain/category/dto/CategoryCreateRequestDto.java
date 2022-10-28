package com.iljungitjung.domain.category.dto;

import com.iljungitjung.domain.category.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryCreateRequestDto {

    private String categoryName;

    private String time;

    private String color;

    public Category toCategoryEntity(CategoryCreateRequestDto categoryCreateRequestDto){
        return Category.builder()
                .categoryName(categoryCreateRequestDto.categoryName)
                .time(categoryCreateRequestDto.time)
                .color(categoryCreateRequestDto.color)
                .build();

    }

}
