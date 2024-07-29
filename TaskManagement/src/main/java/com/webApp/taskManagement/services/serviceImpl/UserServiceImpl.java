package com.webApp.taskManagement.services.serviceImpl;

import com.webApp.taskManagement.dto.UserDTO;
import com.webApp.taskManagement.exceptionHandler.UserException;
import com.webApp.taskManagement.models.User;
import com.webApp.taskManagement.repository.UserRepository;
import com.webApp.taskManagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.webApp.taskManagement.common.UserErrorCode.*;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public UserDTO createUser(UserDTO userDTO) {
    if (userRepository.existsByUserName(userDTO.getUsername())) {
      throw new UserException(HttpStatus.CONFLICT, USERNAME_ALREADY_IN_USE);
    }
    if (userRepository.existsByEmail(userDTO.getEmail())) {
      throw new UserException(HttpStatus.CONFLICT, EMAIL_ALREADY_IN_USE);
    }
    User user = convertToEntity(userDTO);
    user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
    user = userRepository.save(user);
    return convertToDTO(user);
  }

  @Override
  public UserDTO getUserById(String id) {
    Optional<User> userOptional = userRepository.findById(id);
    if (userOptional.isPresent()) {
      return convertToDTO(userOptional.get());
    } else {
      throw new UserException(HttpStatus.NOT_FOUND, USER_NOT_FOUND);
    }
  }

  @Override
  public List<UserDTO> getAllUsers() {
    return userRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
  }

  @Override
  public UserDTO updateUser(String id, UserDTO userDTO) {
    Optional<User> userOptional = userRepository.findById(id);
    if (userOptional.isEmpty()) {
      throw new UserException(HttpStatus.NOT_FOUND, USER_NOT_FOUND);
    }

    User user = userOptional.get();

    if (!user.getUserName().equals(userDTO.getUsername())
            && userRepository.existsByUserName(userDTO.getUsername())) {
      throw new UserException(HttpStatus.CONFLICT, USERNAME_ALREADY_IN_USE);
    }
    if (!user.getEmail().equals(userDTO.getEmail())
            && userRepository.existsByEmail(userDTO.getEmail())) {
      throw new UserException(HttpStatus.CONFLICT, EMAIL_ALREADY_IN_USE);
    }

    user.setUserName(userDTO.getUsername());
    user.setEmail(userDTO.getEmail());
    if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
      user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
    }
    user = userRepository.save(user);
    return convertToDTO(user);
  }

  @Override
  public UserDTO deleteUser(String id) {
    if (!userRepository.existsById(id)) {
      throw new UserException(HttpStatus.NOT_FOUND, USER_NOT_FOUND);
    }
    userRepository.deleteById(String.valueOf(id));
    return UserDTO.builder().message("User deleted successfully").build();
  }

  @Override
  public UserDTO getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentUsername = authentication.getName();
    Optional<User> userOptional = userRepository.findByUserName(currentUsername);
    if (userOptional.isPresent()) {
      return convertToDTO(userOptional.get());
    } else {
      throw new UserException(HttpStatus.NOT_FOUND, CURRENT_NOT_FOUND);
    }
  }

  @Override
  public UserDTO changePassword(String currentPassword, String newPassword) {
    Optional<User> userOptional = userRepository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
    if (userOptional.isEmpty()) {
      throw new UserException(HttpStatus.NOT_FOUND, CURRENT_NOT_FOUND);
    }

    User currentUser = userOptional.get();

    if (!passwordEncoder.matches(currentPassword, currentUser.getPassword())) {
      throw new UserException(HttpStatus.UNAUTHORIZED, CURRENT_PASSWORD_INCORRECT);
    }

    currentUser.setPassword(passwordEncoder.encode(newPassword));
    userRepository.save(currentUser);
    return UserDTO.builder().message("Password changed successfully").build();
  }

  private User convertToEntity(UserDTO userDTO) {
    User user = new User();
    user.setUserName(userDTO.getUsername());
    user.setEmail(userDTO.getEmail());
    user.setRoles(userDTO.getRoles());
    return user;
  }

  private UserDTO convertToDTO(User user) {
    return UserDTO.builder()
            .id(String.valueOf(user.getId()))
            .username(user.getUserName())
            .email(user.getEmail())
            .createdAt(user.getCreatedAt())
            .roles(user.getRoles())
            .build();
  }
}
