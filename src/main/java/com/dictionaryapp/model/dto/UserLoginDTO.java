package com.dictionaryapp.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDTO {

    private Long id;

    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!")

    private String username;

    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")

    private String password;

    public UserLoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserLoginDTO() {
    }
}