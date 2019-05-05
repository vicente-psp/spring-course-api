package com.springcourse.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.*;

import com.springcourse.domain.enums.RequestState;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity(name = "tb_request")
public class Request implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 75, nullable = false)
    private String subject;

    @Column(columnDefinition = "text")
    private String description;

    @Column(name = "creation_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "request_state", length = 12, nullable = false)
    @Enumerated(EnumType.STRING)
    private RequestState requestState;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "request")
    private List<RequestStage> requestStages = new ArrayList<>();

}
