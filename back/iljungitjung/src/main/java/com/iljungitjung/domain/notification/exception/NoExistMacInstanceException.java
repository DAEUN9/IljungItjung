package com.iljungitjung.domain.notification.exception;

public class NoExistMacInstanceException extends RuntimeException{
    public NoExistMacInstanceException(){ super("MAC 인스턴스가 존재하지 않습니다.");}
}
