package com.eureka.smartrecruit.microservice.exception;

public class ResourceNotFoundException extends SystemException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}