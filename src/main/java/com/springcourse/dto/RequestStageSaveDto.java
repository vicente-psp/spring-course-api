package com.springcourse.dto;


import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;
import com.springcourse.domain.enums.RequestState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class RequestStageSaveDto {

    private String description;

    @NotNull(message = "RequestState é obrigatório")
    private RequestState requestState;

    @NotNull(message = "Request é obrigatório")
    private Request request;

    @NotNull(message = "Owner é obrigatório")
    private User owner;

    public RequestStage transformToRequestStage() {
        return new RequestStage(null, null, this.description, this.requestState, this.request, this.owner);
    }

}
