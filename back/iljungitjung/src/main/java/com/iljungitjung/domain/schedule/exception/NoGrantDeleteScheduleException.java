package com.iljungitjung.domain.schedule.exception;

public class NoGrantDeleteScheduleException extends RuntimeException{
    public NoGrantDeleteScheduleException() { super("해당 일정 삭제 권한이 없습니다."); }
}
