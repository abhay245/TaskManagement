package com.webApp.TaskManagement.Controller;

import com.webApp.TaskManagement.DTO.UserDTO;
import com.webApp.TaskManagement.Service.UserService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired UserService userService;

  @PostMapping
  public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
      UserDTO user = userService.createUser(userDTO);
      return ResponseEntity.ok(user);
  }
  @GetMapping("/hello")
  public String getHello(){
      return "Hello World";
  }
  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
      UserDTO user = userService.getUserById(id);
      return ResponseEntity.ok(user);
  }
  @GetMapping
  public ResponseEntity<List<UserDTO>> getAllUsers(){
      List<UserDTO> userDTOList = userService.getAllUsers();
     return ResponseEntity.ok(userDTOList);
  }
  @PutMapping("/{id}")
  public ResponseEntity<UserDTO> updateUser(@PathVariable Long id,@Valid @RequestBody UserDTO userDTO){
      UserDTO user = userService.updateUser(id,userDTO);
      return ResponseEntity.ok(user);
  }
  @DeleteMapping("/{id}")
  public ResponseEntity<UserDTO> deleteUser(@PathVariable Long id){
      UserDTO user = userService.deleteUser(id);
      return ResponseEntity.ok(user);
  }
  @GetMapping("/me")
  public ResponseEntity<UserDTO> getCurrentUser(){
      UserDTO user = userService.getCurrentUser();
      return ResponseEntity.ok(user);
  }
  @PutMapping("/me/password")
  public ResponseEntity<UserDTO> changePassword(@RequestBody ChangePasswordRequest request){
      UserDTO user = userService.changePassword(request.getCurrentPassword(),request.getNewPassword());
      return ResponseEntity.ok(user);
  }

  @Setter
  @Getter
  private static class ChangePasswordRequest {
    private String currentPassword;
    private String newPassword;

  }
}
