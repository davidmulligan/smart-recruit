package com.eureka.smartrecruit.service;

import com.eureka.smartrecruit.domain.Feedback;
import com.eureka.smartrecruit.domain.User;
import com.eureka.smartrecruit.microservice.exception.ResourceNotFoundException;
import com.eureka.smartrecruit.respository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public void create(final Feedback feedback) {
        feedback.setAuthor((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        feedbackRepository.save(feedback);
    }

    public Feedback findById(final Long id) {
        return feedbackRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Could not find feedback with id: %s", id)));
    }
}
