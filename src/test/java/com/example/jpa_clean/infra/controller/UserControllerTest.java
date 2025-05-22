package com.example.jpa_clean.infra.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.jpa_clean.application.usecases.CreateUserUseCase;
import com.example.jpa_clean.domain.entity.User;
import com.example.jpa_clean.infra.controller.user.CreateUserRequest;
import com.example.jpa_clean.infra.controller.user.UserDTOMapper;
import com.example.jpa_clean.infra.gateways.UserRepositoryGateway;
import com.example.jpa_clean.infra.controller.user.CreateUserResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CreateUserUseCase createUserUseCase;

    @MockitoBean
    private UserDTOMapper userDTOMapper;

    @MockitoBean
    private UserRepositoryGateway userRepositoryGateway;

    @Test
    public void shouldCreateUserSuccessfully() throws Exception {
        // Arrange
        CreateUserRequest request = new CreateUserRequest("John Doe", "123", "john@example.com");
        User user = new User("John Doe", "john@example.com", "123");
        User savedUser = new User("John Doe", "john@example.com", "123");

        when(userDTOMapper.toUser(any(CreateUserRequest.class))).thenReturn(user);
        when(createUserUseCase.createUser(any(User.class))).thenReturn(savedUser);
        when(userDTOMapper.toResponse(any(User.class)))
                .thenReturn(new CreateUserResponse("John Doe", "john@example.com"));

        // Act & Assert
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john@example.com"));
    }

    @Test
    public void shouldReturn400WhenInvalidRequest() throws Exception {
        // Arrange
        Object[] request = {}; // Invalid request

        // Act & Assert
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}
