package com.iljungitjung.domain.notification.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@Getter
@RedisHash(value = "phone", timeToLive = 180)
public class Phone {

    @Id
    private String id;
    private String randomNumber;
    private String phonenum;

    @Builder
    Phone(String id, String randomNumber, String phonenum) {
        this.id = id;
        this.randomNumber = randomNumber;
        this.phonenum = phonenum;
    }

    public String getId() {return this.id;}
    public String getRandomNumber() {return this.randomNumber;}
    public String getPhonenum() {return this.phonenum;}
}
