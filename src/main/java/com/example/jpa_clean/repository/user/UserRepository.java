package com.example.jpa_clean.repository.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.jpa_clean.model.UserEntity;

abstract interface UserRepositoryCrud extends CrudRepository<UserEntity, Long> {
}

@Repository
public class UserRepository implements IUserRepository {

    @Autowired
    private UserRepositoryCrud userRepositoryCrud;

    @Override
    public UserEntity save(UserEntity user) {
        return userRepositoryCrud.save(user);
    }

    @Override
    public Iterable<UserEntity> findAll() {
        return userRepositoryCrud.findAll();
    }
}
