package com.webApp.TaskManagement.Service.ServiceImpl;

import com.webApp.TaskManagement.Models.Task;
import com.webApp.TaskManagement.Repository.TaskRepository;
import com.webApp.TaskManagement.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
  @Autowired TaskRepository taskRepository;

  @Override
  public Task createTask(Task task) {
    task.setCreatedAt(LocalDateTime.now());
    task.setUpdatedAt(LocalDateTime.now());
    return taskRepository.save(task);
  }

  @Override
  public Optional<Task> getTaskById(String id) {
    return taskRepository.findById(id);
  }

  @Override
  public List<Task> getTaskByUserId(String userId) {
    return taskRepository.findByUserId(userId);
  }

  @Override
  public List<Task> getTasksByUserIdAndStatus(String userId, Task.TaskStatus status) {
    return taskRepository.findByUserIdAndStatus(userId, status);
  }

  @Override
  public Task updateTask(String id, Task taskDetails) {
    Optional<Task> task = taskRepository.findById(id);
    if (task.isPresent()) {
      Task updatedTask = task.get();
      updatedTask.setTitle(taskDetails.getTitle());
      updatedTask.setDescription(taskDetails.getDescription());
      updatedTask.setStatus(taskDetails.getStatus());
      updatedTask.setPriority(taskDetails.getPriority());
      updatedTask.setDueDate(taskDetails.getDueDate());
      updatedTask.setTags(taskDetails.getTags());
      updatedTask.setUpdatedAt(LocalDateTime.now());
      return taskRepository.save(updatedTask);
    }
    return null;
  }

  @Override
  public void deleteTask(String id) {
    taskRepository.deleteById(id);
  }
}
