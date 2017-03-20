package com.eureka.smartrecruit.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Workroom extends DomainObject {

    @ManyToOne
    private Job job;
}
