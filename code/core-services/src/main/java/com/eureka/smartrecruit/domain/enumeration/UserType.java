package com.eureka.smartrecruit.domain.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UserType {

    CLIENT("Client"),
    USER("User");

    private final String description;

    @JsonValue
    public String getDescription() {
        return description;
    }

    @JsonCreator
    public static UserType fromDescription(String description) {
        if (description != null) {
            for (UserType userType : UserType.values()) {
                if (userType.getDescription().equals(description)) {
                    return userType;
                }
            }
        }
        return null;
    }
}