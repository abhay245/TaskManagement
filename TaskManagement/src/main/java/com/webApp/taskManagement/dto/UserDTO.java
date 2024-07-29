package com.webApp.taskManagement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.webApp.taskManagement.exceptionHandler.UserException;
import jakarta.validation.constraints.*;
import jdk.jshell.spi.ExecutionControl;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    private String id;

    @NotNull
    @JsonProperty("username")
    @Pattern(
            regexp = "^[a-zA-Z0-9_]{5,15}$",
            message =
                    "Username must be between 5 and 15 characters and can only contain letters, numbers, and underscores")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @JsonProperty("email")
    private String email;

    @NotBlank(message = "Password is required")
    @Pattern(
            regexp = "^(?=.*[a-zA-Z])(?=.*\\d).{8,}$",
            message = "Password must be at least 8 characters long and contain both letters and numbers")
    @JsonProperty("password")
    private String password;

    @PastOrPresent(message = "CreatedAt must be in the past or present")
    @JsonProperty("localDateTime")
    private LocalDateTime createdAt;

    private Set<String> roles;
    private String errorMessage;
    private String message;
}
