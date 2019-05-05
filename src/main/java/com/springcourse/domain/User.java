package com.springcourse.domain;

import java.util.ArrayList;
import java.util.List;

import com.springcourse.domain.enums.Role;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class User {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Role role;
    private List<Request> requests = new ArrayList<>();
    private List<RequestStage> requestStages = new ArrayList<>();

}
