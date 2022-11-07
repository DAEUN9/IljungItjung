package com.iljungitjung.domain.category.dto;

import com.iljungitjung.domain.category.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryCreateRequestDto {

    @NotBlank(message = "categoryName은 비워둘 수 없습니다.")
    private String categoryName;

    @Size(min=4, max=4, message = "형식을 맞춰주세요 (ex.0130)")
    private String time;

    @Size(min=7, max=7, message = "형식을 맞춰주세요 (ex.#000000)")
    private String color;

    public Category toCategoryEntity(CategoryCreateRequestDto categoryCreateRequestDto){
        return Category.builder()
                .categoryName(categoryCreateRequestDto.categoryName)
                .time(categoryCreateRequestDto.time)
                .color(categoryCreateRequestDto.color)
                .build();

    }

}
