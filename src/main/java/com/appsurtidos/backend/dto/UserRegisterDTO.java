package com.appsurtidos.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterDTO {
    private String username;
    private String password;
    private String email;

    public UserRegisterDTO() {}
}
