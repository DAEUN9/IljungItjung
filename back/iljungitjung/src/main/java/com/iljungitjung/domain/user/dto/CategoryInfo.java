package com.iljungitjung.domain.user.dto;

import com.iljungitjung.domain.category.entity.Category;
import lombok.Getter;

@Getter
public class CategoryInfo {
    private String categoryName;

    public CategoryInfo(Category category){
        this.categoryName = category.getCategoryName();
    }
}
