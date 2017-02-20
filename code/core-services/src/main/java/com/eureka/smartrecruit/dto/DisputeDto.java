package com.eureka.smartrecruit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DisputeDto {

    private Long id;
    private String subject;
    private String complaint;
    private String reply;
    private String adminReply;
    private boolean resolved;
    private JobDto job;
}
