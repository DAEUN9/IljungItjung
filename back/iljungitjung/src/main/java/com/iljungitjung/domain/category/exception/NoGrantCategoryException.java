package com.iljungitjung.domain.category.exception;

public class NoGrantCategoryException extends RuntimeException{
    public NoGrantCategoryException() { super("카테고리 수정/삭제 권한이 없습니다."); }
}
