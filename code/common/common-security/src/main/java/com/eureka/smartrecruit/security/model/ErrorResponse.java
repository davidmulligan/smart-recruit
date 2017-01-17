package com.eureka.smartrecruit.security.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class ErrorResponse {

    private final String message;
    private final HttpStatus status;
    private final Date timestamp;

    public ErrorResponse(final String message, final HttpStatus status) {
        this(message, status, new Date());
    }

    public Integer getStatus() {
        return status.value();
    }
}