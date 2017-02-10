package com.eureka.smartrecruit.dto;

import com.eureka.smartrecruit.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackDto {

    private Long id;
    private String comment;
    private String rating;
    private User author;
    private User user;
}
