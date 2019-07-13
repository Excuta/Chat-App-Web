package com.piedpiper.piperchat.exception;

/**
 * Created By: Yahia
 */
public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
    }

    public InvalidCredentialsException(String message) {
        super(message);
    }

    public InvalidCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }
}
