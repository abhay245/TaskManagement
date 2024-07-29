package com.webApp.taskManagement.common;

import com.webApp.taskManagement.exceptionHandler.UserException;
import org.springframework.http.HttpStatus;

public class UserErrorCode {
  public static final String USERNAME_ALREADY_IN_USE = "Username is already taken";

  public static final String EMAIL_ALREADY_IN_USE = "Email is already in use";

  public static final String USER_NOT_FOUND = "No user found";

  public static final String CURRENT_NOT_FOUND = "Current User not found";

  public static final String CURRENT_PASSWORD_INCORRECT = "Current password is incorrect";

}
