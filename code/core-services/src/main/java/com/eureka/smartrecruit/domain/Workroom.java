package com.eureka.smartrecruit.domain;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Workroom extends DomainObject {

    @ManyToOne
    @JoinColumn
    private Job job;
}
