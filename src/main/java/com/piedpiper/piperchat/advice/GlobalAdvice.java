package com.piedpiper.piperchat.advice;

import com.piedpiper.piperchat.exception.InvalidCredentialsException;
import com.piedpiper.piperchat.exception.UserAlreadyExistsException;
import com.piedpiper.piperchat.exception.UserNotFoundException;
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
public class GlobalAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class,
        UserAlreadyExistsException.class,
        InvalidCredentialsException.class})
    public ResponseEntity<Object> handleInvalidInput(Exception exception, WebRequest request) {
        return handleExceptionInternal(exception,
                                       null,
                                       new HttpHeaders(),
                                       HttpStatus.BAD_REQUEST,
                                       request);
    }

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<Object> handleNotFound(Exception exception, WebRequest request) {
        return handleExceptionInternal(exception,
                                       null,
                                       new HttpHeaders(),
                                       HttpStatus.NOT_FOUND,
                                       request);
    }
}
