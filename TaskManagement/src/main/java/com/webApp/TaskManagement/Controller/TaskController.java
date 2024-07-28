package com.webApp.TaskManagement.Controller;

import com.webApp.TaskManagement.Models.Task;
import com.webApp.TaskManagement.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class TaskController {
    @Autowired TaskService taskService;
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task){
     Task createdTask = taskService.createTask(task);
     return ResponseEntity.ok(createdTask);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable String id){
        return taskService.getTaskById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable String id, @RequestBody Task taskDetails){
        Task updatedTask = taskService.updateTask(id,taskDetails);
        return updatedTask != null ? ResponseEntity.ok(updatedTask) : ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id){
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }
}
