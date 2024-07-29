package com.webApp.taskManagement.services;

import com.webApp.taskManagement.dto.TaskDTO;
import com.webApp.taskManagement.models.Task;
import java.util.List;
import java.util.Optional;

public interface TaskService {
  TaskDTO createTask(TaskDTO task);

  TaskDTO getTaskById(String id);

  List<TaskDTO> getTaskByUserId(String userId);

  List<TaskDTO> getTasksByUserIdAndStatus(String userId, Task.TaskStatus status);

  TaskDTO updateTask(String id, TaskDTO taskDetails);

  void deleteTask(String id);
}
