package com.iljungitjung.domain.user.dto;

import com.iljungitjung.domain.category.entity.Category;
import com.iljungitjung.domain.user.entity.User;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class UserInfo {
    private String nickname;

    private String email;

    private String imagePath;

    private String description;

    private String introduction;

    private List<CategoryInfo> categories;

    public UserInfo(User user){
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.imagePath = user.getImagePath();
        this.description = user.getDescription();
        this.introduction = user.getIntroduction();
    }

    public void convertCategories(List<Category> categoryList){
        if(categoryList.size() == 0) return;
        this.categories = new ArrayList<>();
        for(Category category : categoryList){
            categories.add(new CategoryInfo(category));
        }
    }

}
