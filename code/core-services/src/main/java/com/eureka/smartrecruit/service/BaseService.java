package com.eureka.smartrecruit.service;

import com.eureka.smartrecruit.domain.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class BaseService {

    protected User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
