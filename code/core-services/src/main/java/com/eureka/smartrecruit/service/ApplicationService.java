package com.eureka.smartrecruit.service;

import com.eureka.smartrecruit.domain.Application;
import com.eureka.smartrecruit.domain.User;
import com.eureka.smartrecruit.microservice.exception.ResourceNotFoundException;
import com.eureka.smartrecruit.respository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public void create(final Application application) {
        application.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        applicationRepository.save(application);
    }

    public void update(final Application application) {
        applicationRepository.save(application);
    }

    public void delete(final Long id) {
        applicationRepository.delete(id);
    }

    public Application findById(final Long id) {
        return applicationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Could not find application with id: %s", id)));
    }
}
