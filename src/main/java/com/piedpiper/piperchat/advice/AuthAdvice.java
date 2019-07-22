package com.piedpiper.piperchat.advice;

import com.piedpiper.piperchat.controller.AuthController;
import com.piedpiper.piperchat.exception.TokenExpiredException;
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
@ControllerAdvice(assignableTypes = {AuthController.class})
@ResponseBody
public class AuthAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler({TokenExpiredException.class})
    public ResponseEntity<Object> handleUnauthorized(Exception exception, WebRequest request) {
        return handleExceptionInternal(exception,
                                       null,
                                       new HttpHeaders(),
                                       HttpStatus.UNAUTHORIZED,
                                       request);
    }
}
