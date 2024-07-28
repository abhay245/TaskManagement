package com.webApp.TaskManagement.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "users")
public class User {
  @Id
  private String id;
  @Indexed(unique = true)
  private String userName;
  @Indexed(unique = true)
  private String email;
  private String password;
  private LocalDateTime createdAt;
  private Set<String> roles = new HashSet<>();
}
