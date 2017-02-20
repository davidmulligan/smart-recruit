package com.eureka.smartrecruit.service;

import com.eureka.smartrecruit.domain.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseService {

    protected User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
