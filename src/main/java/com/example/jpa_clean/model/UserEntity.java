package com.example.jpa_clean.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Table("USER")
public class UserEntity {
    @Id
    private Long id;
    private String username;
    private String password;
    private String email;
    
}
