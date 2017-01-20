package com.eureka.smartrecruit.domain;

import com.eureka.smartrecruit.database.BaseDomainObject;
import com.eureka.smartrecruit.domain.enumeration.UserType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Membership extends BaseDomainObject {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column
    private Double cost;

    @Column(nullable = false)
    private boolean active;

    @Enumerated(EnumType.STRING)
    private UserType userType;
}
