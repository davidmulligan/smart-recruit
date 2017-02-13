package com.eureka.smartrecruit.domain;

import com.eureka.smartrecruit.database.BaseDomainObject;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class DomainObject extends BaseDomainObject {

    @Column
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    @CreatedDate
    private LocalDateTime createdOn;

    @ManyToOne
    @CreatedBy
    private User createdBy;

    @Column
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    @LastModifiedDate
    private LocalDateTime modifiedOn;

    @ManyToOne
    @LastModifiedBy
    private User modifiedBy;
}
