package com.eureka.smartrecruit.dto;

import com.eureka.smartrecruit.database.BaseDomainObject;
import com.eureka.smartrecruit.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto extends BaseDomainObject {

    private User receiver;
    private String subject;
    private String content;
    private boolean read;
    private LocalDateTime readOn;
    private boolean deleted;
    private LocalDateTime deletedOn;
}
