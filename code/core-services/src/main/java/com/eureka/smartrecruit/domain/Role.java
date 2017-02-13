package com.eureka.smartrecruit.domain;

import com.eureka.smartrecruit.database.BaseDomainObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Immutable
@Getter
@NoArgsConstructor
public class Role extends BaseDomainObject {

    @Column(nullable = false)
    private String role;
}
