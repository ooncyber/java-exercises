package com.example.jpa_clean.infra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa_clean.application.usecases.CreateUserUseCase;
import com.example.jpa_clean.domain.entity.User;
import com.example.jpa_clean.infra.controller.user.CreateUserRequest;
import com.example.jpa_clean.infra.controller.user.CreateUserResponse;
import com.example.jpa_clean.infra.controller.user.UserDTOMapper;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private CreateUserUseCase createUserUseCase;

    @Autowired
    private UserDTOMapper userDTOMapper;

    @PostMapping
    CreateUserResponse create(@RequestBody CreateUserRequest userRequest) {
        User user = userDTOMapper.toUser(userRequest);
        User savedUser = createUserUseCase.createUser(user);
        return userDTOMapper.toResponse(savedUser);
    }
}
