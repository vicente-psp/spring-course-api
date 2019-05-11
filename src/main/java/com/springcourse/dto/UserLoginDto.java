package com.springcourse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class UserLoginDto {

    @Email(message = "Endereço de email inválido")
    private String email;

    @NotBlank(message = "Password é obrigatório.")
    private String password;

}
