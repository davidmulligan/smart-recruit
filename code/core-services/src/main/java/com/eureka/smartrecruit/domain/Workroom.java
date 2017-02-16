package com.eureka.smartrecruit.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Workroom extends DomainObject {

    @ManyToOne
    @JoinColumn
    private Job job;
}
