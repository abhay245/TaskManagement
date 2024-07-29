package com.webApp.taskManagement.exceptionHandler;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public class TaskException extends RuntimeException {
    private final HttpStatus status;

    public TaskException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

}
