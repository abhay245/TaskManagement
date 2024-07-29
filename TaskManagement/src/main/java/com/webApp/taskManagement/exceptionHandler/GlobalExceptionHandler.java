package com.webApp.taskManagement.exceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        List<String> errorMessages = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errorMessages.add("Field '" + fieldName + "': " + errorMessage);
        });

        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorMessage(errorMessages)
                .message("Validation failed")
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleUserException(UserException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorMessage(List.of(ex.getMessage()))
                .message("User-related error")
                .build();

        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }
    @ExceptionHandler(TaskException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleTaskException(TaskException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorMessage(List.of(ex.getMessage()))
                .message("Task-related error")
                .build();

        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }
}
