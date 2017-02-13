package com.eureka.smartrecruit.domain;

import com.eureka.smartrecruit.database.BaseDomainObject;
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
    private String comment;

    @Column(nullable = false)
    private Integer rating;

    @ManyToOne
    private User author;

    @ManyToOne
    private User user;

    @ManyToOne
    private Job job;
}
