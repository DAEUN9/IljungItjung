package com.iljungitjung.domain.category.dto;

import com.iljungitjung.domain.category.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class CategoryEditRequestDto {

    @NotNull(message = "id는 필수로 입력해야 합니다.")
    private Long id;
    @NotBlank(message = "categoryName은 비워둘 수 없습니다.")
    private String categoryName;
    @NotBlank(message = "time은 비워둘 수 없습니다.")
    @Size(min=4, max=4)
    private String time;

    @NotBlank(message = "color는 비워둘 수 없습니다.")
    @Size(min=7, max=7)
    private String color;

    public Category toCategoryEntity(CategoryEditRequestDto categoryEditRequestDto){
        return Category.builder()
                .categoryName(categoryEditRequestDto.categoryName)
                .time(categoryEditRequestDto.time)
                .color(categoryEditRequestDto.color)
                .build();

    }

}
