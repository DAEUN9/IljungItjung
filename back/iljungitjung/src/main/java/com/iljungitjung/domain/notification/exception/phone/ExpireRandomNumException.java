package com.iljungitjung.domain.notification.exception.phone;

public class ExpireRandomNumException extends RuntimeException{
    public ExpireRandomNumException(){ super("만료된 인증번호 입니다.");}
}
