package com.eureka.smartrecruit.service;

import com.eureka.smartrecruit.domain.Feedback;
import com.eureka.smartrecruit.domain.User;
import com.eureka.smartrecruit.microservice.exception.ResourceNotFoundException;
import com.eureka.smartrecruit.respository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.jooq.lambda.Seq;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.eureka.smartrecruit.domain.QFeedback.feedback;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final JobService jobService;

    @Transactional
    public void create(final Feedback feedback) {
        jobService.feedback(feedback.getJob());
        feedbackRepository.save(feedback);
    }

    public Feedback findById(final Long id) {
        return feedbackRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Could not find feedback with id: %s", id)));
    }

    public List<Feedback> findByUser(User user) {
        return Seq.seq(feedbackRepository.findAll(feedback.user.eq(user), feedback.createdOn.desc())).toList();
    }
}
