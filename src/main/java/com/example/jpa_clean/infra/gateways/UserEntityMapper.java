package com.example.jpa_clean.infra.gateways;


import com.example.jpa_clean.domain.entity.User;
import com.example.jpa_clean.infra.persistence.user.UserEntity;

public class UserEntityMapper {
  public UserEntity toEntity(User userDomainObj) {
    return new UserEntity(userDomainObj.username(), userDomainObj.password(), userDomainObj.email());
  }

  public User toDomainObj(UserEntity userEntity) {
    return new User(userEntity.getUsername(), userEntity.getPassword(), userEntity.getEmail());
  }

}
