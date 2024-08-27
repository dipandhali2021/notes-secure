package com.secure.notes.services;

import com.secure.notes.dtos.UserDTO;
import com.secure.notes.models.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void updateUserRole(Long userId, String roleName);

    UserDTO getUserById(Long id);
}

