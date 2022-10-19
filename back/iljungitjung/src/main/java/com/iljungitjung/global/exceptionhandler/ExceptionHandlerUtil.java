package com.iljungitjung.global.exceptionhandler;

import com.iljungitjung.global.common.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionHandlerUtil {

    /*  ex) 보고 지우세요
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<CommonResponse> handleMethodArgumentNotValidException(BindingResult bindingResult){
        return ResponseEntity.badRequest().body(CommonResponse.getFailResponse(bindingResult));
    }
    */
}
