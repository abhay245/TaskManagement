package com.webApp.taskManagement.services;

import com.webApp.taskManagement.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserById(String id);
    List<UserDTO> getAllUsers();
    UserDTO updateUser(String id, UserDTO userDTO);
    UserDTO deleteUser(String id);
    UserDTO getCurrentUser();
    UserDTO changePassword(String currentPassword, String newPassword);
}
