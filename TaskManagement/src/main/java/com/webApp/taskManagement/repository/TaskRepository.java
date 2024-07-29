package com.webApp.taskManagement.repository;

import com.webApp.taskManagement.dto.TaskDTO;
import com.webApp.taskManagement.models.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {
  List<Task> findByUserId(String userId);

  List<Task> findByUserIdAndStatus(String userId, Task.TaskStatus status);
}
