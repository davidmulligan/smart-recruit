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
public class Feedback extends DomainObject {

    @Column
    private String title;

    @Column
    private String comment;

    @Column(nullable = false)
    private Integer rating;

    @ManyToOne
    private User user;

    @ManyToOne
    private Job job;
}
