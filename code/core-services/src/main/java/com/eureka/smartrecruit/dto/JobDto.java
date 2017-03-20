package com.eureka.smartrecruit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobDto {

    private Long id;
    private String title;
    private String description;
    private CategoryDto category;
    private Double remuneration;
    private Integer duration;
    private String location;
    private Integer numberPositions;
    private LocalDateTime deadline;
    private boolean fixed;
    private String status;
    private UserDto createdBy;
    private LocalDateTime createdOn;
    private WorkroomDto workroom;
    private List<SkillDto> skills;
    private List<BidDto> bids;
    private List<UserDto> freelancers;
}
