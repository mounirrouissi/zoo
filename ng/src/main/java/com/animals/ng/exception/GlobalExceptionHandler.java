package com.animals.ng.exception;

import com.animals.ng.dto.GlobalResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalResponse> handleException(Exception ex) {
        GlobalResponse response = new GlobalResponse("ERROR", ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


    public static class AnimalNotFoundException extends RuntimeException {
        public AnimalNotFoundException(String message) {
            super(message);
        }
    }
    public static class AnimalAlreadyExistsException extends RuntimeException {
        public AnimalAlreadyExistsException(String message) {
            super(message);
        }
    }
    public static class AnimalTypeNotFoundException extends RuntimeException {
        public AnimalTypeNotFoundException(String message) {
            super(message);
        }
    }
    public static class AnimalTypeAlreadyExistsException extends RuntimeException {
        public AnimalTypeAlreadyExistsException(String message) {
            super(message);
        }
    }
    public static class AnimalTypeNotEmptyException extends RuntimeException {
        public AnimalTypeNotEmptyException(String message) {
            super(message);
        }
    }

}
