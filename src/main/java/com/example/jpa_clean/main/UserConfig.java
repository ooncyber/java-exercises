package com.example.jpa_clean.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.jpa_clean.application.usecases.CreateUserUseCase;
import com.example.jpa_clean.infra.controller.user.UserDTOMapper;
import com.example.jpa_clean.infra.gateways.UserEntityMapper;
import com.example.jpa_clean.infra.gateways.UserRepositoryGateway;
import com.example.jpa_clean.infra.persistence.user.UserRepositoryCrud;

@Configuration
public class UserConfig {
    @Bean
    CreateUserUseCase createUserUseCase(UserRepositoryGateway userGateway, UserEntityMapper userEntityMapper) {
        return new CreateUserUseCase(userGateway, userEntityMapper);
    }

    @Bean
    UserRepositoryGateway userRepositoryGateway(UserRepositoryCrud userRepositoryCrud) {
        return new UserRepositoryGateway(userRepositoryCrud);
    }

    @Bean
    UserEntityMapper userEntityMapper() {
        return new UserEntityMapper();
    }

    @Bean
    UserDTOMapper userDTOMapper() {
        return new UserDTOMapper();
    }
}
