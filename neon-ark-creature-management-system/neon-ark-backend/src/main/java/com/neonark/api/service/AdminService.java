package com.neonark.api.service;

import com.neonark.api.dto.UserResponse;
import com.neonark.api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final UserRepository userRepo;

    public AdminService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public List<UserResponse> getAllUsers() {
        return userRepo.findAll().stream()
                .map(u -> new UserResponse(
                        u.getId(),
                        u.getFullName(),
                        u.getEmail(),
                        u.getPhone(),
                        u.getRole()
                ))
                .toList();
    }
}
