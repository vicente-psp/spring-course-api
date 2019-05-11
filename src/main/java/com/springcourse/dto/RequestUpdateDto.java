package com.springcourse.dto;


import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class RequestUpdateDto {

    @NotBlank(message = "Subject é obrigatório")
    private String subject;

    private String description;

    @NotNull(message = "Owner é obrigatório")
    private User owner;

    private List<RequestStage> requestStages = new ArrayList<>();

    public Request transformToRequest() {
        return new Request(null, this.subject, this.description, null, null, this.owner, this.requestStages);
    }

}
