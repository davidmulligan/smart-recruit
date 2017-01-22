package com.eureka.smartrecruit.domain;

import com.eureka.smartrecruit.database.BaseDomainObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FeedbackItem extends BaseDomainObject {

    @Column(nullable = false)
    private String review;

    @Column(nullable = false)
    private Integer score;
}
