package com.springcourse.dto;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;
import com.springcourse.domain.enums.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class UserUpdateDto {

    @NotBlank(message = "Name é obrigatório")
    private String name;

    @Email(message = "Email inválido")
    private String email;

    @Size(min = 7, max = 99, message = "Password deve ter entre 7 e 99 caracteres")
    private String password;

    private List<Request> requests = new ArrayList<>();
    private List<RequestStage> requestStages = new ArrayList<>();


    public User transformToUser() {
        return new User(null, this.name, this.email, this.password, null, this.requests, this.requestStages);
    }

}
