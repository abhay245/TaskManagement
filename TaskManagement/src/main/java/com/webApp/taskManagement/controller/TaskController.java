package com.webApp.taskManagement.controller;

import com.webApp.taskManagement.dto.TaskDTO;
import com.webApp.taskManagement.exceptionHandler.TaskException;
import com.webApp.taskManagement.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
  @Autowired TaskService taskService;

  @PostMapping
  public ResponseEntity<TaskDTO> createTask(@RequestBody @Valid TaskDTO task) {
    TaskDTO createdTask = taskService.createTask(task);
    return ResponseEntity.ok(createdTask);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TaskDTO> getTaskById(@PathVariable String id) {
    try {
      TaskDTO taskDTO = taskService.getTaskById(id);
      return ResponseEntity.ok(taskDTO);
    } catch (TaskException ex) {
      return ResponseEntity.status(ex.getStatus())
          .body(TaskDTO.builder().errorMessage(ex.getMessage()).build());
    }
  }

  @GetMapping("/user/{id}")
  public ResponseEntity<List<TaskDTO>> getTaskByUserId(@PathVariable String id) {
    try {
      List<TaskDTO> taskDTO = taskService.getTaskByUserId(id);
      return ResponseEntity.ok(taskDTO);
    } catch (TaskException ex) {
      return ResponseEntity.status(ex.getStatus())
          .body(Collections.singletonList(TaskDTO.builder().errorMessage(ex.getMessage()).build()));
    }
  }

  @PatchMapping("/{id}")
  public ResponseEntity<TaskDTO> updateTask(
      @PathVariable String id, @RequestBody @Valid TaskDTO taskDetails) {
    try {
      TaskDTO updatedTask = taskService.updateTask(id, taskDetails);
      return ResponseEntity.ok(updatedTask);
    } catch (TaskException ex) {
      return ResponseEntity.status(ex.getStatus())
          .body(TaskDTO.builder().errorMessage(ex.getMessage()).build());
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<TaskDTO> deleteTask(@PathVariable String id) {
    try {
      TaskDTO task = taskService.getTaskById(id);
      taskService.deleteTask(id);
      return ResponseEntity.ok(task);
    } catch (TaskException exception) {
      return ResponseEntity.status(exception.getStatus())
          .body(TaskDTO.builder().errorMessage(exception.getMessage()).build());
    }
  }
}
