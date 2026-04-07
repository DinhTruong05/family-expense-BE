package org.example.payment.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.payment.Dto.response.UserResponse;
import org.example.payment.Entity.User;
import org.example.payment.exception.ResourceNotFoundException;
import org.example.payment.repository.UserRepository;
import org.example.payment.service.UserService;
import org.example.payment.util.SecurityUtil;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse getMyProfile() {
        String email = SecurityUtil.getCurrentUsername();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }
}