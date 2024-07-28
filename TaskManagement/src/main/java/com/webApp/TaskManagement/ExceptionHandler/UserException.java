package com.webApp.TaskManagement.ExceptionHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class UserException extends RuntimeException {
  @Autowired HttpStatus status;

  public UserException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }
}
