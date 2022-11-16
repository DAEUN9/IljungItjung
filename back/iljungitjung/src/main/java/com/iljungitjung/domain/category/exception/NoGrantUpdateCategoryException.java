package com.iljungitjung.domain.category.exception;

public class NoGrantUpdateCategoryException extends RuntimeException{
    public NoGrantUpdateCategoryException() { super("카테고리 수정 권한이 없습니다."); }
}
