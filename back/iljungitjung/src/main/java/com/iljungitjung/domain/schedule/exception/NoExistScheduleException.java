package com.iljungitjung.domain.schedule.exception;

public class NoExistScheduleException extends RuntimeException{
    public NoExistScheduleException(){
        super("일정이 존재하지 않습니다.");
    }
}
