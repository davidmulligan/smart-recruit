package com.eureka.smartrecruit.domain.enumeration.operations;

import com.eureka.smartrecruit.domain.enumeration.JobStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UnsupportedJobStatusTransitionException extends RuntimeException {

    private final JobStatus from;
    private final JobStatus to;
}
