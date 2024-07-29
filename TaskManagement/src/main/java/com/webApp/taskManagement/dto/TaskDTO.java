package com.webApp.taskManagement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskDTO {

  private String id;
  @Size(max = 100, message = "Title cannot exceed 100 characters")
  @JsonProperty("title")
  @NotBlank(message = "Title is mandatory")
  private String title;

  @Size(max = 500, message = "Description cannot exceed 500 characters")
  @JsonProperty("description")
  private String description;

  @NotNull(message = "Status is mandatory")
  @JsonProperty("status")
  private TaskStatus status;

  @NotNull(message = "Priority is mandatory")
  @JsonProperty("priority")
  private TaskPriority priority;

  @FutureOrPresent(message = "Due date must be in the present or future")
  @JsonProperty("dueDate")
  private LocalDateTime dueDate;

  @Size(max = 50, message = "User ID cannot exceed 50 characters")
  @NotBlank(message = "User ID is mandatory")
  @JsonProperty("userId")
  private String userId;

  @PastOrPresent(message = "Created date cannot be in the future")
  private LocalDateTime createdAt;

  @PastOrPresent(message = "Updated date cannot be in the future")
  private LocalDateTime updatedAt;

  private Set<String> tags = new HashSet<>();
  private String errorMessage;
  public enum TaskStatus {
    TODO,
    IN_PROGRESS,
    COMPLETED
  }

  public enum TaskPriority {
    LOW,
    MEDIUM,
    HIGH
  }
}
