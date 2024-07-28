package com.webApp.TaskManagement.Service;

import com.webApp.TaskManagement.Models.Task;
import java.util.List;
import java.util.Optional;

public interface TaskService {
  Task createTask(Task task);

  Optional<Task> getTaskById(String id);

  List<Task> getTaskByUserId(String userId);

  List<Task> getTasksByUserIdAndStatus(String userId, Task.TaskStatus status);

  Task updateTask(String id, Task taskDetails);

  void deleteTask(String id);
}
