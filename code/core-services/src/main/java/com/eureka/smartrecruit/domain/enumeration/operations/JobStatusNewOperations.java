package com.eureka.smartrecruit.domain.enumeration.operations;

import com.eureka.smartrecruit.domain.Job;
import com.eureka.smartrecruit.domain.enumeration.JobStatus;

public class JobStatusNewOperations extends JobStatusBaseOperations {

    @Override
    public JobStatus approve(Job job) {
        return JobStatus.APPROVED;
    }

    @Override
    public JobStatus reject(Job job) {
        return JobStatus.REJECTED;
    }

    @Override
    public JobStatus archive(Job job) {
        return JobStatus.ARCHIVED;
    }

    @Override
    public JobStatus cancel(Job job) {
        return JobStatus.CANCELLED;
    }
}
