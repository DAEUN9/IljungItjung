package com.iljungitjung.domain.category.dto;

import lombok.Getter;

@Getter
public class CategoryCreateResponseDto {

    private final Long count;

    public CategoryCreateResponseDto(Long count) {
        this.count=count;
    }
}
