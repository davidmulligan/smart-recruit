package com.eureka.smartrecruit.web.controller;

import com.eureka.smartrecruit.domain.Feedback;
import com.eureka.smartrecruit.dto.FeedbackDto;
import com.eureka.smartrecruit.service.FeedbackService;
import com.eureka.smartrecruit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.dozer.Mapper;
import org.jooq.lambda.Seq;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/feedback")
public class FeedbackResource {

    private final FeedbackService feedbackService;
    private final UserService userService;
    private final Mapper mapper;

    @RequestMapping(method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable("userId") Long userId, @RequestBody FeedbackDto feedbackDto) {
        Feedback feedback = mapper.map(feedbackDto, Feedback.class);
        feedback.setUser(userService.findById(userId));
        feedbackService.create(feedback);
    }

    @RequestMapping(method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public List<FeedbackDto> findAll(@PathVariable("userId") Long userId) {
        return Seq.seq(userService.findById(userId).getFeedback()).map(feedback -> mapper.map(feedback, FeedbackDto.class)).toList();
    }
}
