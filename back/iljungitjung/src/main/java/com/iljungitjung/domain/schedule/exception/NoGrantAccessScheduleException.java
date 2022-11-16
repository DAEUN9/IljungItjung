package com.iljungitjung.domain.schedule.exception;

public class NoGrantAccessScheduleException extends RuntimeException{
    public NoGrantAccessScheduleException() { super("해당 일정 접근 권한이 없습니다."); }
}
