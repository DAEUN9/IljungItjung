package com.iljungitjung.domain.notification.exception.notification;

public class NoExistPhonenumException extends RuntimeException{
    public NoExistPhonenumException(){ super("전화번호가 존재하지 않습니다.");}
}
