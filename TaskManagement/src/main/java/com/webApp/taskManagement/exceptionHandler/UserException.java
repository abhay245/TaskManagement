package com.webApp.taskManagement.exceptionHandler;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public class UserException extends RuntimeException {
  private final HttpStatus status;

  public UserException(HttpStatus status, String message) {
    super(message);
    this.status = status;
  }

  public HttpStatus getStatus() {
    return status;
  }
}
