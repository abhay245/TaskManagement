package com.webApp.TaskManagement.Service;

import com.webApp.TaskManagement.DTO.UserDTO;
import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserById(Long id);
    List<UserDTO> getAllUsers();
    UserDTO updateUser(Long id, UserDTO userDTO);
    UserDTO deleteUser(Long id);
    UserDTO getCurrentUser();
    UserDTO changePassword(String currentPassword,String newPassword);
}
