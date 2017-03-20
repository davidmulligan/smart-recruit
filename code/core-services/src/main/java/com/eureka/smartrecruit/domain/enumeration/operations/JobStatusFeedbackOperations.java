package com.eureka.smartrecruit.domain.enumeration.operations;

import com.eureka.smartrecruit.domain.Job;
import com.eureka.smartrecruit.domain.enumeration.JobStatus;

public class JobStatusFeedbackOperations extends JobStatusBaseOperations {

    @Override
    public JobStatus archive(Job job) {
        return JobStatus.ARCHIVED;
    }
}
