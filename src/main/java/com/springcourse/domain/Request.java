package com.springcourse.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.*;

import com.springcourse.domain.enums.RequestState;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Request {

    private Long id;
    private String subject;
    private String description;
    private Date creationDate;
    private RequestState requestState;
    private User user;
    private List<RequestStage> requestStages = new ArrayList<>();

}
