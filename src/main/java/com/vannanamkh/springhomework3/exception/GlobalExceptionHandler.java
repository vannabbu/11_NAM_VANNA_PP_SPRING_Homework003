package com.vannanamkh.springhomework3.exception;
import com.vannanamkh.springhomework3.model.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.method.ParameterValidationResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundExceptionHandler.class)
    public ProblemDetail handleRuntimeException(NotFoundExceptionHandler ex) {

        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        detail.setProperty("timestamp", Instant.now());
        detail.setTitle("Resource not found");

        return detail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ProblemDetail detail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        Map<String, Object> errors = new HashMap<>();

        for(FieldError e : ex.getBindingResult().getFieldErrors()) {
            errors.put(e.getField(), e.getDefaultMessage());
        }

        detail.setProperty("errors", errors);

        return detail;
    }

//    @ExceptionHandler(HandlerMethodValidationException.class)
//    public ProblemDetail handleHandlerMethodValidationException(HandlerMethodValidationException ex) {
//        ProblemDetail detail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
//
//        Map<String, Object> errors = new HashMap<>();
//
//        for (ParameterValidationResult result : ex.getParameterValidationResults()) {
//            String parameter = result.getMethodParameter().getParameterName();
//
//            for (var errorMessage : result.getResolvableErrors()) {
//                errors.put(parameter, errorMessage.getDefaultMessage());
//            }
//
//        }
//
//        detail.setProperty("errors", errors);
//
//        return detail;
//    }

//    @ExceptionHandler(CustomValidationException.class)
//    public ResponseEntity<ErrorResponse> handleCustomValidation(
//            CustomValidationException ex, HttpServletRequest request) {
//
//        ErrorResponse errorBody = new ErrorResponse(
//                request.getRequestURI(),
//                HttpStatus.BAD_REQUEST.value(),
//                "Bad Request",
//                LocalDateTime.now(),
//                ex.getErrors()
//        );
//
//        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ErrorResponse> handleHandlerMethodValidationException(
            HandlerMethodValidationException ex, HttpServletRequest request) {

        Map<String, String> errors = new HashMap<>();

        for (ParameterValidationResult result : ex.getParameterValidationResults()) {
            String parameter = result.getMethodParameter().getParameterName();

            for (var errorMessage : result.getResolvableErrors()) {
                errors.put(parameter, errorMessage.getDefaultMessage());
            }
        }
        ErrorResponse errorBody = ErrorResponse.builder()
                .type("http://localhost:8080/errors/bad-request")
                .instance(request.getRequestURI())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Bad Request")
                .timestamp(LocalDateTime.now())
                .errors(errors)
                .build();
        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<ErrorResponse> handleCustomValidation(
            CustomValidationException ex, HttpServletRequest request) {

        ErrorResponse errorBody = ErrorResponse.builder()
                .type("http://localhost:8080/errors/bad-request")
                .instance(request.getRequestURI())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Bad Request")
                .timestamp(LocalDateTime.now())
                .errors(ex.getErrors())
                .build();;
        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyDataException.class)
    public ResponseEntity<String> handleNotFound(EmptyResultDataAccessException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item already deleted or never existed.");
    }

}
