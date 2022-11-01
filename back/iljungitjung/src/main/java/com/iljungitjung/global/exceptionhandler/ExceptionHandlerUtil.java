package com.iljungitjung.global.exceptionhandler;

import com.iljungitjung.domain.category.exception.NoExistCategoryException;
<<<<<<< back/iljungitjung/src/main/java/com/iljungitjung/global/exceptionhandler/ExceptionHandlerUtil.java
import com.iljungitjung.domain.notification.exception.*;
=======
import com.iljungitjung.domain.schedule.exception.DateFormatErrorException;
import com.iljungitjung.domain.schedule.exception.NoExistScheduleDetailException;
>>>>>>> back/iljungitjung/src/main/java/com/iljungitjung/global/exceptionhandler/ExceptionHandlerUtil.java
import com.iljungitjung.domain.schedule.exception.NoExistScheduleException;
import com.iljungitjung.global.common.CommonResponse;
import com.iljungitjung.global.login.exception.NotMemberException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionHandlerUtil {

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

<<<<<<< back/iljungitjung/src/main/java/com/iljungitjung/global/exceptionhandler/ExceptionHandlerUtil.java
    @ExceptionHandler(ConvertToJsonErrorException.class)
    ResponseEntity<CommonResponse> handleConvertToJsonErrorException(BindingResult bindingResult){
        return ResponseEntity.badRequest().body(CommonResponse.getFailResponse(bindingResult));
    }

    @ExceptionHandler(FailSendMessageException.class)
    ResponseEntity<CommonResponse> handleFailSendMessageException(BindingResult bindingResult){
        return ResponseEntity.badRequest().body(CommonResponse.getFailResponse(bindingResult));
    }

    @ExceptionHandler(InvalidSigningKeyException.class)
    ResponseEntity<CommonResponse> handleInvalidSigningKeyException(BindingResult bindingResult){
        return ResponseEntity.badRequest().body(CommonResponse.getFailResponse(bindingResult));
    }

    @ExceptionHandler(MacFinalMessageEncodingException.class)
    ResponseEntity<CommonResponse> handleMacFinalMessageEncodingException(BindingResult bindingResult){
        return ResponseEntity.badRequest().body(CommonResponse.getFailResponse(bindingResult));
    }

    @ExceptionHandler(MessageUriSyntaxErrorException.class)
    ResponseEntity<CommonResponse> handleMessageUriSyntaxErrorException(BindingResult bindingResult){
        return ResponseEntity.badRequest().body(CommonResponse.getFailResponse(bindingResult));
    }

    @ExceptionHandler(NoExistMacInstanceException.class)
    ResponseEntity<CommonResponse> handleNoExistMacInstanceException(BindingResult bindingResult){
        return ResponseEntity.badRequest().body(CommonResponse.getFailResponse(bindingResult));
    }

    @ExceptionHandler(SecretKeyEncodingException.class)
    ResponseEntity<CommonResponse> handleSecretKeyEncodingException(BindingResult bindingResult){
        return ResponseEntity.badRequest().body(CommonResponse.getFailResponse(bindingResult));
    }
}
