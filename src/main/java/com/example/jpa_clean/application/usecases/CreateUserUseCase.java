package com.example.jpa_clean.application.usecases;

import com.example.jpa_clean.domain.entity.User;
import com.example.jpa_clean.infra.gateways.UserEntityMapper;
import com.example.jpa_clean.infra.gateways.UserRepositoryGateway;
import com.example.jpa_clean.infra.persistence.user.UserEntity;

public class CreateUserUseCase {
    private UserEntityMapper userEntityMapper;
    private UserRepositoryGateway userRepositoryGateway;

    public CreateUserUseCase(UserRepositoryGateway userRepositoryGateway, UserEntityMapper userEntityMapper) {
        this.userRepositoryGateway = userRepositoryGateway;
        this.userEntityMapper = new UserEntityMapper();
    }


    public User createUser(User user) {
        UserEntity userEntity = this.userEntityMapper.toEntity(user);
        UserEntity savedUserEntity = userRepositoryGateway.save(userEntity);
        return userEntityMapper.toDomainObj(savedUserEntity);
    }
}
