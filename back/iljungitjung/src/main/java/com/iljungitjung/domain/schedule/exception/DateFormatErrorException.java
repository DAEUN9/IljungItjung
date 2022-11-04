package com.iljungitjung.domain.schedule.exception;

public class DateFormatErrorException extends RuntimeException{
    public DateFormatErrorException(){
        super("날짜 입력 형식이 틀렸습니다.");
    }
}