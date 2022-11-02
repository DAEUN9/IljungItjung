package com.iljungitjung.domain.category.exception;

public class NoExistCategoryException extends RuntimeException{
    public NoExistCategoryException() { super("카테고리가 존재하지 않습니다."); }
}
