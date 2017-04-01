package com.eureka.smartrecruit.domain.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MessageType {

    MESSAGE1("Message", "Message1", "message1");

    private final String subject;
    private final String content;
    private final String emailTemplate;
}