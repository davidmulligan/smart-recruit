package com.eureka.smartrecruit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackDto {

    private Long id;
    private String title;
    private String comment;
    private Integer rating;
    private UserDto user;
    private UserDto createdBy;
    private LocalDateTime createdOn;
}
