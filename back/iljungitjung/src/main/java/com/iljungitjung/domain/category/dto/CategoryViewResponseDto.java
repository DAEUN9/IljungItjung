package com.iljungitjung.domain.category.dto;

import com.iljungitjung.domain.category.entity.Category;
import lombok.Getter;

@Getter
public class CategoryViewResponseDto {

     private final Long id;

    private final String cateogryName;

    private final String color;

    private final String time;

    public CategoryViewResponseDto(Category category) {
        this.id=category.getId();
        this.cateogryName=category.getCategoryName();
        this.color= category.getColor();
        this.time=category.getTime();
    }
}
