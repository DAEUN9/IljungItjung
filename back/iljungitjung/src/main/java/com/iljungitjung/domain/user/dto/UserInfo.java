package com.iljungitjung.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.iljungitjung.domain.category.entity.Category;
import com.iljungitjung.domain.user.entity.User;
import com.iljungitjung.global.login.entity.RedisUser;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfo {
    private String nickname;

    private String email;

    private String imagePath;

    private List<CategoryInfo> categories;

    public UserInfo(RedisUser redisUser){
        this.nickname = redisUser.getNickname();
        this.email = redisUser.getEmail();
        this.imagePath = redisUser.getProfileImg();
    }

    public UserInfo(User user){
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.imagePath = user.getImagePath();
    }

    public void convertCategories(List<Category> categoryList){
        if(categoryList.size() == 0) return;
        this.categories = new ArrayList<>();
        for(Category category : categoryList){
            categories.add(new CategoryInfo(category));
        }
    }
}
