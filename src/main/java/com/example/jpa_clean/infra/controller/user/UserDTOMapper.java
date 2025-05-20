package com.example.jpa_clean.infra.controller.user;

import com.example.jpa_clean.domain.entity.User;

public class UserDTOMapper {
  public CreateUserResponse toResponse(User user) {
    return new CreateUserResponse(user.username(), user.email());
  }

  public User toUser(CreateUserRequest request) {
    return new User(request.username(), request.password(), request.email());
  }
}

