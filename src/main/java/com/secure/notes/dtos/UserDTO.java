package com.secure.notes.dtos;

import com.secure.notes.models.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long userId;
    private String username;
    private String email;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private LocalDate credentialsExpiredDate;
    private LocalDate accountExpiredDate;
    private String twoFactorSecret;
    private boolean twoFactorEnabled;
    private String signUpMethod;
    private Role role;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
