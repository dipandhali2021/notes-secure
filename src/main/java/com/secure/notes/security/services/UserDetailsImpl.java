package com.secure.notes.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.secure.notes.models.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@NoArgsConstructor
@Data
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private String username;
    private Long id;
    private String email;

    @JsonIgnore
    private String password;

    private boolean is2faEnabled;

    private Collection<? extends GrantedAuthority> authorities;


    public UserDetailsImpl(String username, Long id, String email, String password, boolean is2faEnabled, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.id = id;
        this.email = email;
        this.password = password;
        this.is2faEnabled = is2faEnabled;
        this.authorities = authorities;
    }

    
  public static UserDetailsImpl build(User user) {

        GrantedAuthority Authority = new SimpleGrantedAuthority(user.getRole().toString());

        return new UserDetailsImpl(
                user.getUsername(),
                user.getUserId(),
                user.getEmail(),
                user.getPassword(),
                user.isTwoFactorEnabled(),
                List.of(Authority)
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public boolean is2faEnabled() {
        return is2faEnabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

