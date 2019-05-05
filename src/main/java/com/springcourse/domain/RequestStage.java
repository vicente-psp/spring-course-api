package com.springcourse.domain;

import java.util.Date;

import lombok.*;

import com.springcourse.domain.enums.RequestState;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class RequestStage {

    private Long id;
    private Date realizationDate;
    private String description;
    private RequestState requestState;
    private Request request;
    private User user;

}
