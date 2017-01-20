package com.eureka.smartrecruit.dto;

import com.eureka.smartrecruit.database.BaseDomainObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto extends BaseDomainObject {

    private UserDto sender;
    private UserDto recipient;
    private String content;
    private String type;
    private boolean read;
    private LocalDateTime readOn;
}
