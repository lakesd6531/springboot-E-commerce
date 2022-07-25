package com.mark.springbootmall.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserRegisterRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
