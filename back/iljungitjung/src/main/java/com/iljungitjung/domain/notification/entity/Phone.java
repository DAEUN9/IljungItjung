package com.iljungitjung.domain.notification.entity;

import com.iljungitjung.domain.notification.dto.PhoneConfirmRequestDto;
import com.iljungitjung.domain.notification.exception.phone.IncorrectPhonenumException;
import com.iljungitjung.domain.notification.exception.phone.IncorrectRandomNumberException;
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

    public void checkCorrect(PhoneConfirmRequestDto requestDto) {
        checkPhonenum(requestDto.getPhonenum());
        checkRandomNumber(requestDto.getRandomNumber());
    }

    private void checkPhonenum(String other) {
        if(!phonenum.equals(other)) {
            throw new IncorrectPhonenumException();
        }
    }

    private void checkRandomNumber(String other) {
        if(!randomNumber.equals(other)) {
            throw new IncorrectRandomNumberException();
        }
    }

}
