package com.autohub.service;

import com.autohub.api.dto.ProfileResponse;
import com.autohub.api.dto.ProfileUpdateRequest;
import com.autohub.domain.User;
import com.autohub.repo.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProfileService {
    private final UserRepository userRepository;
    private final CurrentUserService currentUserService;

    public ProfileService(UserRepository userRepository, CurrentUserService currentUserService) {
        this.userRepository = userRepository;
        this.currentUserService = currentUserService;
    }

    public ProfileResponse getProfile() {
        User user = getCurrentUser();
        return new ProfileResponse(user.getId(), user.getPhone(), user.getRole().name(), user.getName(), user.getCreatedAt());
    }

    public ProfileResponse updateProfile(ProfileUpdateRequest request) {
        User user = getCurrentUser();
        user.setName(request.getName());
        userRepository.save(user);
        return new ProfileResponse(user.getId(), user.getPhone(), user.getRole().name(), user.getName(), user.getCreatedAt());
    }

    private User getCurrentUser() {
        Long userId = currentUserService.getUserId();
        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }
}
