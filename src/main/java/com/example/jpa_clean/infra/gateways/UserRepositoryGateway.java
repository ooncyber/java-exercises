package com.example.jpa_clean.infra.gateways;

import com.example.jpa_clean.domain.repository.IUserRepository;
import com.example.jpa_clean.infra.persistence.user.UserEntity;
import com.example.jpa_clean.infra.persistence.user.UserRepositoryCrud;


public class UserRepositoryGateway implements IUserRepository{
    private UserRepositoryCrud userRepositoryCrud;

    public UserRepositoryGateway(UserRepositoryCrud userRepositoryCrud) {
        this.userRepositoryCrud = userRepositoryCrud;
    }

    @Override
    public UserEntity save(UserEntity user) {
        return userRepositoryCrud.save(user);
    }
}
