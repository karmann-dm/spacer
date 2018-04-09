package com.devspace.spacer.service;

import com.devspace.spacer.model.User;
import com.devspace.spacer.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByEmail(String email);
    User save(UserRegistrationDto registrationDto);
}
