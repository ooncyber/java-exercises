package com.example.jpa_clean.infra.persistence.user;

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
    
    public UserEntity(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
