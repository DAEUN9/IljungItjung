package com.iljungitjung.global.exceptionhandler;

import com.iljungitjung.domain.category.exception.NoExistCategoryException;
import com.iljungitjung.domain.notification.exception.notification.ConvertToJsonErrorException;
import com.iljungitjung.domain.notification.exception.notification.FailSendMessageException;
import com.iljungitjung.domain.notification.exception.notification.FailSignatureKeyErrorException;
import com.iljungitjung.domain.notification.exception.notification.MessageUriSyntaxErrorException;
import com.iljungitjung.domain.notification.exception.phone.IncorrectPhonenumException;
import com.iljungitjung.domain.notification.exception.phone.IncorrectRandomNumberException;
import com.iljungitjung.domain.schedule.exception.DateFormatErrorException;
import com.iljungitjung.domain.schedule.exception.NoExistScheduleDetailException;
import com.iljungitjung.domain.schedule.exception.NoExistScheduleException;
import com.iljungitjung.domain.user.exception.AlreadyExistUserException;
import com.iljungitjung.global.common.CommonResponse;
import com.iljungitjung.global.login.exception.ExpireRedisUserException;
import com.iljungitjung.global.login.exception.NotMemberException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.Iterator;


@RestControllerAdvice
public class ExceptionHandlerUtil {

    @Value("${login.kakao.register_client_uri}")
    private String KAKAO_REGISTER_CLIENT_URI;

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
    void handleNotMemberException(NotMemberException e, HttpServletResponse response){
        try {
            response.sendRedirect(KAKAO_REGISTER_CLIENT_URI);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
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

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<CommonResponse> handleConstraintViolationException(ConstraintViolationException e){
        Iterator<ConstraintViolation<?>> iterator = e.getConstraintViolations().iterator();
        ConstraintViolation<?> error = iterator.next();

        return ResponseEntity.badRequest().body(CommonResponse.getErrorResponse(error.getMessage()));
    }

    @ExceptionHandler(IncorrectPhonenumException.class)
    ResponseEntity<CommonResponse> handleIncorrectPhonenumException(BindingResult bindingResult) {
        return ResponseEntity.badRequest().body(CommonResponse.getFailResponse(bindingResult));
    }

    @ExceptionHandler(IncorrectRandomNumberException.class)
    ResponseEntity<CommonResponse> handleIncorrectRandomNumberException(BindingResult bindingResult) {
        return ResponseEntity.badRequest().body(CommonResponse.getFailResponse(bindingResult));
    }
}
