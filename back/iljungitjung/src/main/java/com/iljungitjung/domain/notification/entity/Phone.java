package com.iljungitjung.domain.notification.entity;

import com.iljungitjung.domain.notification.dto.PhoneConfirmRequestDto;
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

    public boolean checkCorrect(PhoneConfirmRequestDto requestDto) {
        return checkPhonenum(requestDto.getPhonenum()) && checkRandomNumber(requestDto.getRandomNumber());
    }

    private boolean checkPhonenum(String phonenum) {
        return this.phonenum.equals(phonenum);
    }

    private boolean checkRandomNumber(String randomNumber) {
        return this.randomNumber.equals(randomNumber);
    }
}
