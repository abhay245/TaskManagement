package com.webApp.taskManagement.exceptionHandler;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ErrorResponse {
    private List<String> errorMessage;
    private String message;
}
