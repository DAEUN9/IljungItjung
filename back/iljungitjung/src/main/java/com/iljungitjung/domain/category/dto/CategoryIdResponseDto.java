package com.iljungitjung.domain.category.dto;

import lombok.Getter;

@Getter
public class CategoryIdResponseDto {

    private final Long id;

    public CategoryIdResponseDto(Long id) {
        this.id=id;
    }
}
