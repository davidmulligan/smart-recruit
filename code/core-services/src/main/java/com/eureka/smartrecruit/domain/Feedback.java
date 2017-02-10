package com.eureka.smartrecruit.domain;

import com.eureka.smartrecruit.database.BaseDomainObject;
import com.eureka.smartrecruit.domain.enumeration.Rating;
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
public class Feedback extends BaseDomainObject {

    @Column
    private String comment;

    @Column(nullable = false)
    private Rating rating;

    @ManyToOne
    private User author;

    @ManyToOne
    private User user;

    @ManyToOne
    private Job job;
}
