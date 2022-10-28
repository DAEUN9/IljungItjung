package com.iljungitjung.domain.user.entity;


import com.iljungitjung.domain.schedule.entity.Schedule;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String nickname;
    @Column(nullable = false)
    private String phonenum;
    private String imagePath;
    private String kakaoToken;
    private String openTime;
    private String closeTime;

    @OneToMany(mappedBy = "userTo", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Schedule> scheduleRequestList = new ArrayList<>();

    @OneToMany(mappedBy = "userFrom", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Schedule> scheduleResponseList = new ArrayList<>();


    public Users(String nickname, String phonenum, String imagePath, String kakaoToken, String openTime, String closeTime) {
        this.nickname = nickname;
        this.phonenum = phonenum;
        this.imagePath = imagePath;
        this.kakaoToken = kakaoToken;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }
}
