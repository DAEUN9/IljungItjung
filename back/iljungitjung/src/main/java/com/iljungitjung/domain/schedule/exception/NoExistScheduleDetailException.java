package com.iljungitjung.domain.schedule.exception;

public class NoExistScheduleDetailException extends RuntimeException{
    public NoExistScheduleDetailException(){
        super("일정의 상세 정보가 존재하지 않습니다.");
    }
}
