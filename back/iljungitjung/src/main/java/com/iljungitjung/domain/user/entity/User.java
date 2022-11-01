package com.iljungitjung.domain.user.entity;


import com.iljungitjung.domain.category.entity.Category;
import com.iljungitjung.domain.schedule.entity.Schedule;
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

    private String kakaoToken;

    private String openTime;

    private String closeTime;

    @Column(unique = true)
    private String email;

    private String description;

    private List<String> tag = new ArrayList<>();

    @OneToMany(mappedBy = "userTo", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Schedule> scheduleRequestList = new ArrayList<>();

    @OneToMany(mappedBy = "userFrom", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Schedule> scheduleResponseList = new ArrayList<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Category> categoryList = new ArrayList<>();

    public void signUp(TemporaryUser temporaryUser){
        this.email = temporaryUser.getEmail();
        this.kakaoToken = temporaryUser.getRefreshToken();
    }

    @Builder
    public User(String nickname, String phonenum, String imagePath, String kakaoToken, String openTime, String closeTime, String email, String description) {
        this.nickname = nickname;
        this.phonenum = phonenum;
        this.imagePath = imagePath;
        this.kakaoToken = kakaoToken;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.email = email;
        this.description = description;
    }
}
