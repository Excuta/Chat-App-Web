package com.piedpiper.piperchat.exception;

/**
 * Created By: Yahia
 */
public class IncorrectCredentialsException extends RuntimeException {
    public IncorrectCredentialsException() {
    }

    public IncorrectCredentialsException(String message) {
        super(message);
    }

    public IncorrectCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }
}
