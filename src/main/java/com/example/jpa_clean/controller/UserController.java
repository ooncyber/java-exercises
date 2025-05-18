package com.example.jpa_clean.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa_clean.model.UserEntity;
import com.example.jpa_clean.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    UserEntity create(@RequestBody UserEntity user) {
        return this.userService.create(user);
    }

    @GetMapping
    Iterable<UserEntity> getUser() {
        return this.userService.getUser();
    }
}
