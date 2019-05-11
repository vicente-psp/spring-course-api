package com.springcourse.dto;


import com.springcourse.domain.enums.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class UserUpdateRoleDto {

    @NotNull(message = "Role é obrigatório")
    private Role role;

}
