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
public class Address extends BaseDomainObject {

    @Column
    private String street1;

    @Column
    private String street2;

    @Column
    private String street3;

    @Column
    private String town;

    @Column
    private String county;

    @Column
    private String postCode;
}