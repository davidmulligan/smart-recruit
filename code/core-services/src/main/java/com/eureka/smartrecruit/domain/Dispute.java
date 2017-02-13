package com.eureka.smartrecruit.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Dispute extends DomainObject {

    @Column(nullable = false)
    private String subject;

    @Column
    private String complaint;

    @Column
    private String reply;

    @Column
    private String adminReply;

    @Column
    private boolean resolved;

    @ManyToOne
    private Job job;
}
