package com.eureka.smartrecruit.web.controller;

import com.eureka.smartrecruit.domain.Feedback;
import com.eureka.smartrecruit.domain.User;
import com.eureka.smartrecruit.dto.FeedbackDto;
import com.eureka.smartrecruit.service.FeedbackService;
import com.eureka.smartrecruit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/{userId}")
public class FeedbackResource {

    private final FeedbackService feedbackService;
    private final UserService userService;
    private final Mapper mapper;

    @RequestMapping(value="/feedback", method= RequestMethod.POST, produces={ "application/json" })
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable("userId") Long userId, @RequestBody FeedbackDto feedbackDto) {
        User user = userService.findById(userId);
        Feedback feedback = mapper.map(feedbackDto, Feedback.class);
        feedbackService.create(feedback, user);
    }

    @RequestMapping(value="/feedback", method=RequestMethod.GET, produces={ "application/json" })
    public List<FeedbackDto> findAll(@PathVariable("userId") Long userId) {
        User user = userService.findById(userId);
        return user.getFeedback().stream().map(feedback -> mapper.map(feedback, FeedbackDto.class)).collect(Collectors.toList());
    }
}
