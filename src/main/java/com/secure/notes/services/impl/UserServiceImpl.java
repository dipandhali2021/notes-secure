package com.secure.notes.services.impl;

import com.secure.notes.dtos.UserDTO;
import com.secure.notes.models.AppRole;
import com.secure.notes.models.Role;
import com.secure.notes.models.User;
import com.secure.notes.repositories.RoleRepository;
import com.secure.notes.repositories.UserRepository;
import com.secure.notes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void updateUserRole(Long userId, String roleName) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        AppRole appRole = AppRole.valueOf(roleName);
        Role role = roleRepository.findByRoleName(appRole).orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRole(role);
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return convertToDTO(user);
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        userDTO.setAccountNonLocked(user.isAccountNonLocked());
        userDTO.setAccountNonExpired(user.isAccountNonExpired());
        userDTO.setCredentialsNonExpired(user.isCredentialsNonExpired());
        userDTO.setEnabled(user.isEnabled());
        userDTO.setCredentialsExpiredDate(user.getCredentialsExpiredDate());
        userDTO.setAccountExpiredDate(user.getAccountExpiredDate());
        userDTO.setTwoFactorSecret(user.getTwoFactorSecret());
        userDTO.setTwoFactorEnabled(user.isTwoFactorEnabled());
        userDTO.setSignUpMethod(user.getSignUpMethod());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setUpdatedAt(user.getUpdatedAt());
        return userDTO;
    }



}
