package com.eureka.smartrecruit.dto;

import com.eureka.smartrecruit.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackDto {

    private Long id;
    private String review;
    private Integer averageScore;
    private User author;
    private User user;
    private Set<FeedbackItemDto> feedbackItems;
}
