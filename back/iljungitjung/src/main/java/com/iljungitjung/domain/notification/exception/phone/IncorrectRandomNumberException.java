package com.iljungitjung.domain.notification.exception.phone;

public class IncorrectRandomNumberException extends RuntimeException{

    public IncorrectRandomNumberException(){ super("일치하지 않는 인증번호 입니다.");}
}
