package com.eureka.smartrecruit.domain.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum NotificationType {

    TYPE1("TYPE1"),
    TYPE2("TYPE2");

    private final String description;

    @JsonValue
    public String getDescription() {
        return description;
    }

    @JsonCreator
    public static NotificationType fromDescription(String description) {
        if (description != null) {
            for (NotificationType userType : NotificationType.values()) {
                if (userType.getDescription().equals(description)) {
                    return userType;
                }
            }
        }
        return null;
    }
}