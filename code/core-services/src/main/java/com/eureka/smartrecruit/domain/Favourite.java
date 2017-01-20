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
public class Favourite extends BaseDomainObject {

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private User user;
}
