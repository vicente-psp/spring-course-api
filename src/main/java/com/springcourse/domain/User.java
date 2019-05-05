package com.springcourse.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class User {

    private Long id;
    private String name;
    private String email;
    private String password;
    private List<Request> requests = new ArrayList<>();
    private List<RequestStage> requestStages = new ArrayList<>();

}
