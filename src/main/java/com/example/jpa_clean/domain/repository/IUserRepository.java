package com.example.jpa_clean.domain.repository;

import com.example.jpa_clean.infra.persistence.user.UserEntity;

public interface IUserRepository {
    UserEntity save(UserEntity user);
}
