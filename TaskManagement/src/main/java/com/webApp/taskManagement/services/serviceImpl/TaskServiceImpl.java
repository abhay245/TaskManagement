package com.webApp.taskManagement.services.serviceImpl;

import com.webApp.taskManagement.dto.TaskDTO;
import com.webApp.taskManagement.exceptionHandler.UserException;
import com.webApp.taskManagement.models.Task;
import com.webApp.taskManagement.repository.TaskRepository;
import com.webApp.taskManagement.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.webApp.taskManagement.common.TaskErrorCode.TASK_NOT_FOUND;

@Service
public class TaskServiceImpl implements TaskService {

  @Autowired private TaskRepository taskRepository;

  @Override
  public TaskDTO createTask(TaskDTO taskDTO) {
    taskDTO.setCreatedAt(LocalDateTime.now());
    taskDTO.setUpdatedAt(LocalDateTime.now());
    Task task = convertToEntity(taskDTO);
    Task savedTask = taskRepository.save(task);
    return convertToDTO(savedTask);
  }

  @Override
  public TaskDTO getTaskById(String id) {
    Optional<Task> taskOptional = taskRepository.findById(id);
    if (taskOptional.isPresent()) {
      return convertToDTO(taskOptional.get());
    } else {
      throw new UserException(HttpStatus.NOT_FOUND, TASK_NOT_FOUND);
    }
  }

  @Override
  public List<TaskDTO> getTaskByUserId(String userId) {
    List<Task> taskList = taskRepository.findByUserId(userId);
    return taskList.stream().map(this::convertToDTO).toList();
  }

  @Override
  public List<TaskDTO> getTasksByUserIdAndStatus(String userId, Task.TaskStatus status) {
    List<Task> taskList = taskRepository.findByUserIdAndStatus(userId, status);
    return taskList.stream().map(this::convertToDTO).toList();
  }

  @Override
  public TaskDTO updateTask(String id, TaskDTO taskDetails) {
    Optional<Task> taskOptional = taskRepository.findById(id);
    if (taskOptional.isPresent()) {
      Task existingTask = taskOptional.get();
      existingTask.setId(taskDetails.getId());
      existingTask.setTitle(taskDetails.getTitle());
      existingTask.setDescription(taskDetails.getDescription());
      existingTask.setStatus(convertToTaskStatus(taskDetails.getStatus()));
      existingTask.setPriority(convertToTaskPriority(taskDetails.getPriority()));
      existingTask.setDueDate(taskDetails.getDueDate());
      existingTask.setTags(taskDetails.getTags());
      existingTask.setUpdatedAt(LocalDateTime.now());
      Task savedTask = taskRepository.save(existingTask);
      return convertToDTO(savedTask);
    } else {
      throw new UserException(HttpStatus.NOT_FOUND, TASK_NOT_FOUND);
    }
  }

  @Override
  public void deleteTask(String id) {
    Optional<Task> task = taskRepository.findById(id);
    if (task.isEmpty()) {
      throw new UserException(HttpStatus.NOT_FOUND, TASK_NOT_FOUND);
    }
    taskRepository.deleteById(id);
  }

  private Task convertToEntity(TaskDTO taskDTO) {
    Task task = new Task();
    task.setId(taskDTO.getId());
    task.setTitle(taskDTO.getTitle());
    task.setDescription(taskDTO.getDescription());
    task.setStatus(convertToTaskStatus(taskDTO.getStatus()));
    task.setPriority(convertToTaskPriority(taskDTO.getPriority()));
    task.setDueDate(taskDTO.getDueDate());
    task.setUserId(taskDTO.getUserId());
    task.setTags(taskDTO.getTags());
    task.setCreatedAt(taskDTO.getCreatedAt());
    task.setUpdatedAt(taskDTO.getUpdatedAt());
    return task;
  }

  private Task.TaskStatus convertToTaskStatus(TaskDTO.TaskStatus dtoStatus) {
    return Task.TaskStatus.valueOf(dtoStatus.name());
  }

  private Task.TaskPriority convertToTaskPriority(TaskDTO.TaskPriority dtoPriority) {
    return Task.TaskPriority.valueOf(dtoPriority.name());
  }

  private TaskDTO convertToDTO(Task task) {
    return TaskDTO.builder()
        .id(String.valueOf(task.getId()))
        .title(task.getTitle())
        .description(task.getDescription())
        .status(convertToTaskDTOStatus(task.getStatus()))
        .priority(convertToTaskDTOPriority(task.getPriority()))
        .dueDate(task.getDueDate())
        .userId(task.getUserId())
        .tags(task.getTags())
        .createdAt(task.getCreatedAt())
        .updatedAt(task.getUpdatedAt())
        .build();
  }

  private TaskDTO.TaskStatus convertToTaskDTOStatus(Task.TaskStatus status) {
    return TaskDTO.TaskStatus.valueOf(status.name());
  }

  private TaskDTO.TaskPriority convertToTaskDTOPriority(Task.TaskPriority priority) {
    return TaskDTO.TaskPriority.valueOf(priority.name());
  }
}
