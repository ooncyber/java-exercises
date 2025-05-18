package com.example.jpa_clean.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.jpa_clean.model.UserEntity;


public interface UserRepository extends CrudRepository<UserEntity, Long> {
    
}
