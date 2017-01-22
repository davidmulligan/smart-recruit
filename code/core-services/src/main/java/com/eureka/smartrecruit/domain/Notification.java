package com.eureka.smartrecruit.domain;

import com.eureka.smartrecruit.database.BaseDomainObject;
import com.eureka.smartrecruit.domain.enumeration.NotificationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Notification extends BaseDomainObject {

    @ManyToOne
    @JoinColumn
    private User sender;

    @ManyToOne
    @JoinColumn
    private User recipient;

    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    @Column(nullable = false)
    private boolean opened;

    @Column
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime openedOn;
}
