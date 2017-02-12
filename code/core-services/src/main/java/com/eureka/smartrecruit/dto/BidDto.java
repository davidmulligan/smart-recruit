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
public class BidDto {

    private Long id;
    private String comment;
    private Double quote;
    private UserDto user;
    private boolean accepted;
    private LocalDateTime acceptedOn;
}
