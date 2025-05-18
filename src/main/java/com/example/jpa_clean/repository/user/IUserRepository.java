package com.example.jpa_clean.repository.user;

import com.example.jpa_clean.model.UserEntity;

public interface IUserRepository {
    UserEntity save(UserEntity user);
    Iterable<UserEntity> findAll();
}
