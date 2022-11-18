package com.iljungitjung.domain.category.exception;

public class NoGrantDeleteCategoryException extends RuntimeException{
    public NoGrantDeleteCategoryException() { super("카테고리 삭제 권한이 없습니다."); }
}
