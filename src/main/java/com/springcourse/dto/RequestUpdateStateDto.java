package com.springcourse.dto;


import com.springcourse.domain.enums.RequestState;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class RequestUpdateStateDto {

    @NotNull(message = "State é obrigatório")
    private RequestState state;

}
