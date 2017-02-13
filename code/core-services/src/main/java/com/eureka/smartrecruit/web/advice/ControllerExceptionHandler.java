package com.eureka.smartrecruit.web.advice;

import com.eureka.smartrecruit.dto.ErrorDto;
import com.eureka.smartrecruit.microservice.exception.ResourceNotFoundException;
import com.eureka.smartrecruit.microservice.exception.SystemException;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler(SystemException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorDto handleSystemException(SystemException e) {
        return new ErrorDto(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorDto handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ErrorDto(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorDto handleConstraintViolationException(ConstraintViolationException e) {
        List<String> constraintViolations = new ArrayList<>();
        e.getConstraintViolations().forEach(constraintViolation -> constraintViolations.add(constraintViolation.getMessage()));
        return new ErrorDto("Validation Errors: " + StringUtils.collectionToCommaDelimitedString(constraintViolations));
    }
}
