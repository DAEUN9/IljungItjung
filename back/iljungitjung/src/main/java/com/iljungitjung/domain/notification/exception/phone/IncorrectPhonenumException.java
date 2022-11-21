package com.iljungitjung.domain.notification.exception.phone;

public class IncorrectPhonenumException extends RuntimeException{
    public IncorrectPhonenumException(){ super("일치하지 않는 전화번호 입니다.");}
}
