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
public class MessageDto extends BaseDomainObject {

    private String subject;
    private String content;
    private boolean opened;
    private LocalDateTime openedOn;
    private boolean deleted;
    private LocalDateTime deletedOn;
    private UserDto sender;
    private UserDto recipient;
}
