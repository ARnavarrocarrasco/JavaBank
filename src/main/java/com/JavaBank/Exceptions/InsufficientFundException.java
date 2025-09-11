package com.JavaBank.Exceptions;

public class InsufficientFundException extends Exception {
    public InsufficientFundException(String errorMessage) {
        super(errorMessage);
    }
}
