package com.example.jpa_clean.infra.gateway;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.jpa_clean.domain.entity.User;
import com.example.jpa_clean.infra.gateways.UserEntityMapper;
import com.example.jpa_clean.infra.persistence.user.UserEntity;


@ExtendWith(MockitoExtension.class)
public class UserEntityMapperTest {

    @InjectMocks
    private UserEntityMapper userEntityMapper;

    @Test
    public void testToEntity() {

        // Arrange
        User user = new User("testUser", "testEmail@", "password123");
        UserEntity expectedUserEntity = new UserEntity("testUser", "password123", "testEmail@");

        // Act
        UserEntity actualUserEntity = userEntityMapper.toEntity(user);
        assertEquals(expectedUserEntity.getUsername(), actualUserEntity.getUsername());
        assertEquals(expectedUserEntity.getPassword(), actualUserEntity.getPassword());
        assertEquals(expectedUserEntity.getEmail(), actualUserEntity.getEmail());
    }

    @Test
    public void testToDomainObj() {
        // Arrange
        UserEntity userEntity = new UserEntity("testUser", "password123", "testEmail@");
        User expectedUser = new User("testUser", "testEmail@", "password123");

        // Act
        User actualUser = userEntityMapper.toDomainObj(userEntity);

        // Assert
        assertEquals(expectedUser.username(), actualUser.username());
        assertEquals(expectedUser.email(), actualUser.email());
        assertEquals(expectedUser.password(), actualUser.password());
    }
}
