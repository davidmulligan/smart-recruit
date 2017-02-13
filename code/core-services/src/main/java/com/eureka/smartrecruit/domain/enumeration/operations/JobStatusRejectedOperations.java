package com.eureka.smartrecruit.domain.enumeration.operations;

import com.eureka.smartrecruit.domain.Job;
import com.eureka.smartrecruit.domain.enumeration.JobStatus;

public class JobStatusRejectedOperations extends JobStatusBaseOperations {

    @Override
    public JobStatus archive(Job job) {
        return JobStatus.ARCHIVED;
    }
}
