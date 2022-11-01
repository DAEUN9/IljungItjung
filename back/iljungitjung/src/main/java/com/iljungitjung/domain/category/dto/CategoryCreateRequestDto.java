package com.iljungitjung.domain.category.dto;

import com.iljungitjung.domain.category.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class CategoryCreateRequestDto {

    @NotBlank(message = "categoryName은 비워둘 수 없습니다.")
    private String categoryName;

    @NotBlank(message = "time은 비워둘 수 없습니다.")
    @Size(min=4, max=4)
    private String time;

    @NotBlank(message = "color는 비워둘 수 없습니다.")
    @Size(min=7, max=7)
    private String color;

    public Category toCategoryEntity(CategoryCreateRequestDto categoryCreateRequestDto){
        return Category.builder()
                .categoryName(categoryCreateRequestDto.categoryName)
                .time(categoryCreateRequestDto.time)
                .color(categoryCreateRequestDto.color)
                .build();

    }

}
