package com.springcourse.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.*;

import com.springcourse.domain.enums.RequestState;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity(name = "tb_request_stage")
@SequenceGenerator(name = "request_stage_seq", sequenceName = "request_stage_seq", initialValue = 1, allocationSize = 1)
public class RequestStage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "request_stage_seq")
    private Long id;

    @Column(name = "realization_date", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date realizationDate;

    @Column(columnDefinition = "text")
    private String description;

    @Column(name = "request_state", length = 12, nullable = false)
    @Enumerated(EnumType.STRING)
    private RequestState requestState;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request request;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

}
