package com.iljungitjung.domain.user.entity;


import com.iljungitjung.domain.category.entity.Category;
import com.iljungitjung.domain.schedule.entity.Schedule;
import com.iljungitjung.domain.user.dto.UpdateUser;
import com.iljungitjung.global.login.entity.TemporaryUser;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true)
    private String nickname;

    @Column(unique = true)
    private String phonenum;

    private String imagePath;

    private String openTime;

    private String closeTime;

    @Column(unique = true)
    private String email;

    private String description;

    private String introduction;

    @OneToMany(mappedBy = "userTo", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Schedule> scheduleRequestList = new ArrayList<>();

    @OneToMany(mappedBy = "userFrom", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Schedule> scheduleResponseList = new ArrayList<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Category> categoryList = new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public void updateUser(UpdateUser updateUserDto){
        this.nickname = updateUserDto.getNickname();
        this.description = updateUserDto.getDescription();
        this.introduction = updateUserDto.getIntroduction();
    }

    public void signUp(TemporaryUser temporaryUser){
        this.email = temporaryUser.getEmail();
        this.imagePath = temporaryUser.getProfileImg();
    }

    @Builder
    public User(String nickname, String phonenum, String imagePath, String openTime, String closeTime, String email, String description, String introduction) {
        this.nickname = nickname;
        this.phonenum = phonenum;
        this.imagePath = imagePath;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.email = email;
        this.description = description;
        this.introduction = introduction;
    }
}
