package com.eureka.smartrecruit.service;

import com.eureka.smartrecruit.domain.Notification;
import com.eureka.smartrecruit.microservice.exception.ResourceNotFoundException;
import com.eureka.smartrecruit.respository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void create(final Notification notification) {
        notificationRepository.save(notification);
    }

    public void delete(final Long id) {
        notificationRepository.delete(id);
    }

    public Notification findById(final Long id) {
        return notificationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Could not find notification with id: %s", id)));
    }
}
