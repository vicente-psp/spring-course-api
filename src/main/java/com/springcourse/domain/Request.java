package com.springcourse.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import com.springcourse.domain.enums.RequestState;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity(name = "tb_request")
@SequenceGenerator(name = "request_seq", sequenceName = "request_seq", initialValue = 1, allocationSize = 1)
public class Request implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "request_seq")
    private Long id;

    @Column(length = 75, nullable = false)
    private String subject;

    @Column(columnDefinition = "text")
    private String description;

    @Column(name = "creation_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "request_state", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private RequestState requestState;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Getter(onMethod = @__({@JsonIgnore}))
    @OneToMany(mappedBy = "request")
    private List<RequestStage> requestStages = new ArrayList<>();

}
