package com.springcourse.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import com.springcourse.domain.enums.Role;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity(name = "tb_user")
@SequenceGenerator(name = "user_seq", sequenceName = "user_seq", initialValue = 1, allocationSize = 1)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private Long id;

    @Column(length = 75, nullable = false)
    private String name;

    @Column(length = 75, nullable = false, unique = true)
    private String email;

    @Getter(onMethod = @__({@JsonIgnore}))
    @Setter(onMethod = @__({@JsonProperty}))
    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 20, nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Getter(onMethod = @__({@JsonIgnore}))
    @OneToMany(mappedBy = "owner")
    private List<Request> requests = new ArrayList<>();

    @Getter(onMethod = @__({@JsonIgnore}))
    @OneToMany(mappedBy = "owner")
    private List<RequestStage> requestStages = new ArrayList<>();

}