package com.piedpiper.piperchat.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created By: Yahia
 */
@ControllerAdvice
@ResponseBody
public class RegistrationAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleInvalidInput(Exception exception, WebRequest request) {
        return handleExceptionInternal(exception,
                                       null,
                                       new HttpHeaders(),
                                       HttpStatus.BAD_REQUEST,
                                       request);
    }
}
