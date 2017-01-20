package com.eureka.smartrecruit.domain;

import com.eureka.smartrecruit.database.BaseDomainObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Feedback extends BaseDomainObject {

    @Column(nullable = false)
    private String review;

    @Column(nullable = false)
    private Float averageRating;

    @ManyToOne
    private User author;

    @OneToOne
    private User user;

    @OneToOne
    private Project project;
}
