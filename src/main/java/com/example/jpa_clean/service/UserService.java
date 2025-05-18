package com.example.jpa_clean.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jpa_clean.model.UserEntity;
import com.example.jpa_clean.repository.user.IUserRepository;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;

    public UserEntity create(UserEntity user){
        return userRepository.save(user);
    }

    public Iterable<UserEntity> getUser(){
        return userRepository.findAll();
    }
}
