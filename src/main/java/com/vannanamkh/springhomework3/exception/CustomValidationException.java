package com.vannanamkh.springhomework3.exception;

import java.util.HashMap;
import java.util.Map;

public class CustomValidationException extends RuntimeException {
    private final Map<String, String> errors;
    private String message ;
    public CustomValidationException(Map<String, String> errors) {
        this.errors = errors;
    }

    public CustomValidationException(String field, String message) {
        this.errors = new HashMap<>();
        this.errors.put(field, message);
    }
    public Map<String, String> getErrors() {
        return errors;
    }
}
