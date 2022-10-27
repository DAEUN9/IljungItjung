package com.iljungitjung.domain.user.entity;

import com.iljungitjung.domain.schedule.entity.Type;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String nickname;

    private String phonenum;

    private String image_path;

    private String kakao_token;

    @Column(name="open_time")
    private String openTime;

    @Column(name="close_time")
    private String closeTime;

    @Builder
    public User(Long id, String nickname, String phonenum, String image_path, String kakao_token, String openTime, String closeTime) {
        this.id = id;
        this.nickname = nickname;
        this.phonenum = phonenum;
        this.image_path = image_path;
        this.kakao_token = kakao_token;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }
}