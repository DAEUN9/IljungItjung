package com.iljungitjung.domain.category.dto;

import com.iljungitjung.domain.category.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryCreateDto {

<<<<<<<< HEAD:back/iljungitjung/src/main/java/com/iljungitjung/domain/category/dto/CategoryCreateDto.java
    @NotBlank(message = "categoryName은 필수 입력 값입니다.")
    @Pattern(regexp = "^[a-z|A-Z|0-9|ㄱ-ㅎ|가-힣]{2,20}$", message = "카테고리 이름은 최소 2자, 최대 20자 영어, 한글, 숫자만 입력가능합니다.")
========
    @NotBlank(message = "categoryName은 비워둘 수 없습니다.")
>>>>>>>> 8894e2efa8e9f7482a0ac9d4246bfa980328d58f:back/iljungitjung/src/main/java/com/iljungitjung/domain/category/dto/CategoryCreateRequestDto.java
    private String categoryName;

    @Size(min=4, max=4, message = "형식을 맞춰주세요 (ex.0130)")
    private String time;

<<<<<<<< HEAD:back/iljungitjung/src/main/java/com/iljungitjung/domain/category/dto/CategoryCreateDto.java
    @Size(min=7, max=7, message = "color 형식을 맞춰주세요 (ex.#000000)")
========
    @Size(min=7, max=7, message = "형식을 맞춰주세요 (ex.#000000)")
>>>>>>>> 8894e2efa8e9f7482a0ac9d4246bfa980328d58f:back/iljungitjung/src/main/java/com/iljungitjung/domain/category/dto/CategoryCreateRequestDto.java
    private String color;

    public Category toCategoryEntity(CategoryCreateRequestDto categoryCreateRequestDto){
        return Category.builder()
                .categoryName(categoryCreateRequestDto.categoryName)
                .time(categoryCreateRequestDto.time)
                .color(categoryCreateRequestDto.color)
                .build();

    }

}
