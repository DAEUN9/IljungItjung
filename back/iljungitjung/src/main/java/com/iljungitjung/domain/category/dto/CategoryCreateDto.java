package com.iljungitjung.domain.category.dto;

import com.iljungitjung.domain.category.entity.Category;
import lombok.*;
import javax.validation.constraints.Pattern;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryCreateDto {

    private String categoryName;
    @Pattern(regexp = "^[0-9]{4}$", message = "time은 4자리 숫자만 입력가능합니다. (ex.0130)")
    private String time;
    @Pattern(regexp = "^#[a-z|A-Z|0-9]{6}$", message = "color 형식을 맞춰주세요. (ex.#000000)")
    private String color;

    public Category toEntity(){
        return Category.builder()
                .categoryName(this.categoryName)
                .time(this.time)
                .color(this.color)
                .build();

    }
}

