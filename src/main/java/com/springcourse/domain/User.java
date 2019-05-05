package com.springcourse.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.*;

import com.springcourse.domain.enums.Role;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity(name = "tb_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 75, nullable = false)
    private String name;

    @Column(length = 75, nullable = false, unique = true)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

//    @OneToMany(mappedBy = "user")
//    private List<Request> requests = new ArrayList<>();
//
//    @OneToMany(mappedBy = "user")
//    private List<RequestStage> requestStages = new ArrayList<>();

}


/* feito teste com outra classe gerada para teste, gerou normalmente a tabela no bd
só não está gerando essa bendita tabela dessa classe user
*/