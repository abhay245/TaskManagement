package com.webApp.TaskManagement.Service.ServiceImpl;

import com.webApp.TaskManagement.DTO.UserDTO;
import com.webApp.TaskManagement.ExceptionHandler.UserException;
import com.webApp.TaskManagement.Models.User;
import com.webApp.TaskManagement.Repository.UserRepository;
import com.webApp.TaskManagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
  @Autowired UserRepository userRepository;
  @Autowired PasswordEncoder passwordEncoder;

  public UserDTO createUser(UserDTO userDTO) {
    if (userRepository.existsByUserName(userDTO.getUsername())) {
      throw new UserException("Username is already taken", HttpStatus.CONFLICT);
    }
    if (userRepository.existsByEmail(userDTO.getEmail())) {
      throw new UserException("Email is already in use",HttpStatus.CONFLICT);
    }
    User user = convertToEntity(userDTO);
    user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
    user = userRepository.save(user);
    return convertToDTO(user);
  }

  public UserDTO getUserById(Long id) {
    User user =
        userRepository
            .findById(String.valueOf(id))
            .orElseThrow(() -> new UserException("User not found with id: "+id,HttpStatus.NOT_FOUND));
    return convertToDTO(user);
  }

  public List<UserDTO> getAllUsers() {
    return userRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
  }

  public UserDTO updateUser(Long id, UserDTO userDTO) {
    User user =
        userRepository
            .findById(String.valueOf(id))
            .orElseThrow(() -> new UserException("User not found with id: "+id,HttpStatus.NOT_FOUND));

    if (!user.getUserName().equals(userDTO.getUsername())
        && userRepository.existsByUserName(userDTO.getUsername())) {
      throw new UserException("Username is already taken",HttpStatus.CONFLICT);
    }
    if (!user.getEmail().equals(userDTO.getEmail())
        && userRepository.existsByEmail(userDTO.getEmail())) {
      throw new UserException("Email is already in use",HttpStatus.CONFLICT);
    }

    user.setUserName(userDTO.getUsername());
    user.setEmail(userDTO.getEmail());
    if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
      user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
    }
    user = userRepository.save(user);
    return convertToDTO(user);
  }

  public UserDTO deleteUser(Long id) {
    if (!userRepository.existsById(String.valueOf(id))) {
      throw new UserException("User not found with id: "+ id,HttpStatus.NOT_FOUND);
    }
    userRepository.deleteById(String.valueOf(id));
    return null;
  }

  public UserDTO getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentUsername = authentication.getName();
    User user =
        userRepository
            .findByUserName(currentUsername)
            .orElseThrow(() -> new UserException("Current user not found",HttpStatus.NOT_FOUND));
    return convertToDTO(user);
  }

  public UserDTO changePassword(String currentPassword, String newPassword) {
    User currentUser =
        userRepository
            .findByUserName(SecurityContextHolder.getContext().getAuthentication().getName())
            .orElseThrow(() ->  new UserException("Current user not found",HttpStatus.NOT_FOUND));

    if (!passwordEncoder.matches(currentPassword, currentUser.getPassword())) {
      throw new UserException("Current password is incorrect",HttpStatus.UNAUTHORIZED);
    }

    currentUser.setPassword(passwordEncoder.encode(newPassword));
    userRepository.save(currentUser);
    return null;
  }

  private User convertToEntity(UserDTO userDTO) {
    User user = new User();
    user.setUserName(userDTO.getUsername());
    user.setEmail(userDTO.getEmail());
    user.setRoles(userDTO.getRoles());
    return user;
  }

  private UserDTO convertToDTO(User user) {
    return UserDTO.builder().id(Long.valueOf(user.getId())).username(user.getUserName()).email(user.getEmail()).createdAt(user.getCreatedAt()).roles(user.getRoles()).build();
  }
}
