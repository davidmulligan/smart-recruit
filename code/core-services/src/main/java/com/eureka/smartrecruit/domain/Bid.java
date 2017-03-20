package com.eureka.smartrecruit.domain;

import com.eureka.smartrecruit.domain.enumeration.BidStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Bid extends DomainObject {

    @Column
    private String comment;

    @Column
    private Double quote;

    @Enumerated(EnumType.STRING)
    private BidStatus status;

    @ManyToOne
    private Job job;
}
