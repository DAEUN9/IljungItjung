package com.iljungitjung.domain.category.dto;

import com.iljungitjung.domain.category.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryEditRequestDto {

    @NotNull(message = "id는 필수로 입력해야 합니다.")
    @Pattern(regexp = "^[0-9]+$", message = "id는 숫자만 입력가능합니다.")
    private Long id;
    @NotBlank(message = "categoryName은 필수 입력 값입니다.")
    @Pattern(regexp = "^[a-z|A-Z|0-9|ㄱ-ㅎ|가-힣]{2,10}$", message = "카테고리 이름은 최소 2자, 최대 10자 영어, 한글, 숫자만 입력가능합니다.")
    private String categoryName;
    @Size(min=4, max=4, message = "형식을 맞춰주세요 (ex.0130)")
    @Pattern(regexp = "^[0-9]+$", message = "time은 숫자만 입력가능합니다.")
    private String time;

    @Size(min=7, max=7, message = "형식을 맞춰주세요 (ex.#000000)")
    @Pattern(regexp = "^#[0-9]+$", message = "color 형식을 맞춰주세요 (ex.#000000)")
    private String color;

    public Category toEntity(){
        return Category.builder()
                .categoryName(this.categoryName)
                .time(this.time)
                .color(this.color)
                .build();

    }

}
