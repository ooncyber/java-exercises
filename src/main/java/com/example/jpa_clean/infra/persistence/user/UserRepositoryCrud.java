package com.example.jpa_clean.infra.persistence.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepositoryCrud extends CrudRepository<UserEntity, Long> {
}