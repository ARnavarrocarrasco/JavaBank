package com.JavaBank.Exceptions;

public class NotFoundAccountException extends  RuntimeException {
    public NotFoundAccountException(String message) {
        super(message);
    }
}
