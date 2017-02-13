package com.eureka.smartrecruit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MembershipDto {

    private Long id;
    private String name;
    private String description;
    private Double cost;
    private Integer jobPostLimit;
    private Integer jobBidLimit;
    private boolean active;
    private String userType;
}
