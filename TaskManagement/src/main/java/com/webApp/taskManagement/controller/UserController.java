package com.webApp.taskManagement.controller;

import com.webApp.taskManagement.dto.UserDTO;
import com.webApp.taskManagement.exceptionHandler.UserException;
import com.webApp.taskManagement.services.UserService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping
  public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
    try {
      UserDTO user = userService.createUser(userDTO);
      return ResponseEntity.ok(user);
    } catch (UserException ex) {
      return ResponseEntity.status(ex.getStatus())
              .body(UserDTO.builder().errorMessage(ex.getMessage()).build());
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> getUserById(@PathVariable("id") String id) {
    try {
      UserDTO user = userService.getUserById(id);
      return ResponseEntity.ok(user);
    } catch (UserException ex) {
      return ResponseEntity.status(ex.getStatus())
              .body(UserDTO.builder().errorMessage(ex.getMessage()).build());
    }
  }

  @GetMapping
  public ResponseEntity<List<UserDTO>> getAllUsers() {
    try {
      List<UserDTO> userDTOList = userService.getAllUsers();
      return ResponseEntity.ok(userDTOList);
    } catch (UserException ex) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
              .body(null);
    }
  }

  @PatchMapping("/{id}")
  public ResponseEntity<UserDTO> updateUser(
          @PathVariable("id") String id, @Valid @RequestBody UserDTO userDTO) {
    try {
      UserDTO user = userService.updateUser(id, userDTO);
      return ResponseEntity.ok(user);
    } catch (UserException ex) {
      return ResponseEntity.status(ex.getStatus())
              .body(UserDTO.builder().errorMessage(ex.getMessage()).build());
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<UserDTO> deleteUser(@PathVariable("id") String id) {
    try {
      UserDTO user = userService.deleteUser(id);
      return ResponseEntity.ok(user);
    } catch (UserException ex) {
      return ResponseEntity.status(ex.getStatus())
              .body(UserDTO.builder().errorMessage(ex.getMessage()).build());
    }
  }

  @GetMapping("/me")
  public ResponseEntity<UserDTO> getCurrentUser() {
    try {
      UserDTO user = userService.getCurrentUser();
      return ResponseEntity.ok(user);
    } catch (UserException ex) {
      return ResponseEntity.status(ex.getStatus())
              .body(UserDTO.builder().errorMessage(ex.getMessage()).build());
    }
  }

  @PutMapping("/me/password")
  public ResponseEntity<UserDTO> changePassword(@RequestBody ChangePasswordRequest request) {
    try {
      UserDTO user =
              userService.changePassword(request.getCurrentPassword(), request.getNewPassword());
      return ResponseEntity.ok(user);
    } catch (UserException ex) {
      return ResponseEntity.status(ex.getStatus())
              .body(UserDTO.builder().errorMessage(ex.getMessage()).build());
    }
  }

  @Setter
  @Getter
  private static class ChangePasswordRequest {
    private String currentPassword;
    private String newPassword;
  }
}