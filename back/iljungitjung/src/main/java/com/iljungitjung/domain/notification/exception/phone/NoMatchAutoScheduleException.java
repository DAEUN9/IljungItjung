package com.iljungitjung.domain.notification.exception.phone;

public class NoMatchAutoScheduleException extends RuntimeException{
    public NoMatchAutoScheduleException(){super("일치하는 스케줄 타입이 없습니다.");}
}
