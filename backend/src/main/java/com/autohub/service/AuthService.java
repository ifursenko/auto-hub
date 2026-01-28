package com.autohub.service;

import com.autohub.api.dto.AuthLoginRequest;
import com.autohub.api.dto.AuthRegisterRequest;
import com.autohub.api.dto.AuthResponse;
import com.autohub.config.JwtService;
import com.autohub.domain.User;
import com.autohub.domain.UserRole;
import com.autohub.repo.UserRepository;
import java.util.Locale;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public AuthResponse register(AuthRegisterRequest request) {
        if (userRepository.findByPhone(request.getPhone()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone already registered");
        }
        User user = new User();
        user.setPhone(request.getPhone());
        user.setName(request.getName());
        user.setRole(UserRole.valueOf(request.getRole().toUpperCase(Locale.ROOT)));
        user = userRepository.save(user);
        return new AuthResponse(user.getId(), user.getRole().name(), user.getName(), jwtService.generateToken(user));
    }

    public AuthResponse login(AuthLoginRequest request) {
        User user = userRepository.findByPhone(request.getPhone())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return new AuthResponse(user.getId(), user.getRole().name(), user.getName(), jwtService.generateToken(user));
    }
}
