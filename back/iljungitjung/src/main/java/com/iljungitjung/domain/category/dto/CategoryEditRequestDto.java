package com.iljungitjung.domain.category.dto;

import com.iljungitjung.domain.category.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class CategoryEditRequestDto {

    private Long id;

    private String categoryName;

    private String time;

    private String color;

    public Category toCategoryEntity(CategoryEditRequestDto categoryEditRequestDto){
        return Category.builder()
                .categoryName(categoryEditRequestDto.categoryName)
                .time(categoryEditRequestDto.time)
                .color(categoryEditRequestDto.color)
                .build();

    }

}
