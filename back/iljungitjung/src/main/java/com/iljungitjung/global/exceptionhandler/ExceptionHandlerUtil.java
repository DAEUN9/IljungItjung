package com.iljungitjung.global.exceptionhandler;

import com.iljungitjung.domain.category.exception.NoExistCategoryException;
import com.iljungitjung.domain.notification.exception.*;
import com.iljungitjung.domain.schedule.exception.DateFormatErrorException;
import com.iljungitjung.domain.schedule.exception.NoExistScheduleDetailException;
import com.iljungitjung.domain.schedule.exception.NoExistScheduleException;
import com.iljungitjung.domain.user.exception.AlreadyExistUserException;
import com.iljungitjung.global.common.CommonResponse;
import com.iljungitjung.global.login.exception.ExpireRedisUserException;
import com.iljungitjung.global.login.exception.NotMemberException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionHandlerUtil {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<CommonResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){

        String message = e.getBindingResult().getFieldError().getDefaultMessage();

        return ResponseEntity.badRequest().body(CommonResponse.getErrorResponse(message));
    }

    @ExceptionHandler(NoExistScheduleException.class)
    ResponseEntity<CommonResponse> handleNoExistScheduleException(BindingResult bindingResult){
        return ResponseEntity.badRequest().body(CommonResponse.getFailResponse(bindingResult));
    }
    @ExceptionHandler(NoExistScheduleDetailException.class)
    ResponseEntity<CommonResponse> v(BindingResult bindingResult) {
        return ResponseEntity.badRequest().body(CommonResponse.getFailResponse(bindingResult));
    }
    @ExceptionHandler(DateFormatErrorException.class)
    ResponseEntity<CommonResponse> handleDateFormatErrorException(BindingResult bindingResult) {
        return ResponseEntity.badRequest().body(CommonResponse.getFailResponse(bindingResult));
    }

    @ExceptionHandler(NoExistCategoryException.class)
    ResponseEntity<CommonResponse> handleNoExistCategoryException(BindingResult bindingResult) {
        return ResponseEntity.badRequest().body(CommonResponse.getFailResponse(bindingResult));
    }

    @ExceptionHandler(NotMemberException.class)
    ResponseEntity<CommonResponse> handleNotMemberException(NotMemberException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(CommonResponse.getErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(ConvertToJsonErrorException.class)
    ResponseEntity<CommonResponse> handleConvertToJsonErrorException(BindingResult bindingResult){
        return ResponseEntity.badRequest().body(CommonResponse.getFailResponse(bindingResult));
    }

    @ExceptionHandler(FailSendMessageException.class)
    ResponseEntity<CommonResponse> handleFailSendMessageException(BindingResult bindingResult){
        return ResponseEntity.badRequest().body(CommonResponse.getFailResponse(bindingResult));
    }

    @ExceptionHandler(MessageUriSyntaxErrorException.class)
    ResponseEntity<CommonResponse> handleMessageUriSyntaxErrorException(BindingResult bindingResult){
        return ResponseEntity.badRequest().body(CommonResponse.getFailResponse(bindingResult));
    }

    @ExceptionHandler(FailSignatureKeyErrorException.class)
    ResponseEntity<CommonResponse> FailSignatureKeyErrorException(BindingResult bindingResult){
        return ResponseEntity.badRequest().body(CommonResponse.getFailResponse(bindingResult));
    }


    @ExceptionHandler(AlreadyExistUserException.class)
    ResponseEntity<CommonResponse> handleAlreadyExistUserException(AlreadyExistUserException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(CommonResponse.getErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(ExpireRedisUserException.class)
    ResponseEntity<CommonResponse> handleExpireRedisUserException(ExpireRedisUserException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(CommonResponse.getErrorResponse(e.getMessage()));
    }
}
