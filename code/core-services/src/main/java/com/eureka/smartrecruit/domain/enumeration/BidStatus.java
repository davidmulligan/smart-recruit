package com.eureka.smartrecruit.domain.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BidStatus {

    PENDING("Pending"),
    ACCEPTED("Accepted"),
    REJECTED("Rejected"),
    CANCELLED("Cancelled");

    private final String description;

    @JsonValue
    public String getDescription() {
        return description;
    }

    @JsonCreator
    public static BidStatus fromDescription(String description) {
        if (description != null) {
            for (BidStatus bidStatus : BidStatus.values()) {
                if (bidStatus.getDescription().equals(description)) {
                    return bidStatus;
                }
            }
        }
        return null;
    }
}
