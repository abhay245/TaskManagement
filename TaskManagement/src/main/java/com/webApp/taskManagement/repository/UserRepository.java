package com.webApp.taskManagement.repository;

import com.webApp.taskManagement.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
  Optional<User> findByUserName(String userName);

  Optional<User> findByEmail(String email);

  boolean existsByUserName(String userName);

  boolean existsByEmail(String email);
}
