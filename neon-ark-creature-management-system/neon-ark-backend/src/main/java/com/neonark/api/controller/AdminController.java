package com.neonark.api.controller;

import com.neonark.api.dto.UserResponse;
import com.neonark.api.service.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
public class AdminController {

    private final AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }

    // Gets a list of all users in the system
    @GetMapping
    public List<UserResponse> getAllUsers() {
        return service.getAllUsers();
    }
}
