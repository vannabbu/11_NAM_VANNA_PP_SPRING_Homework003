package com.vannanamkh.springhomework3.exception;

public class NotFoundExceptionHandler extends RuntimeException{
    public NotFoundExceptionHandler(String message) {
        super(message);
    }
}
