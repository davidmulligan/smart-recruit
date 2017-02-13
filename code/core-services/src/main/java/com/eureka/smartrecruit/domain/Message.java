package com.eureka.smartrecruit.domain;

import com.eureka.smartrecruit.database.BaseDomainObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Message extends BaseDomainObject {

    @Column(nullable = false)
    private String subject;

    @Column
    private String content;

    @Column(nullable = false)
    private boolean opened;

    @Column
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime openedOn;

    @Column(nullable = false)
    private boolean deleted;

    @Column
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime deletedOn;

    @ManyToOne
    @JoinColumn
    private User sender;

    @ManyToOne
    @JoinColumn
    private User recipient;
}
