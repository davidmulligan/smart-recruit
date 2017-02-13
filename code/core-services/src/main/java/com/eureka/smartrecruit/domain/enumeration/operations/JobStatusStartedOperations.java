package com.eureka.smartrecruit.domain.enumeration.operations;

import com.eureka.smartrecruit.domain.Job;
import com.eureka.smartrecruit.domain.enumeration.JobStatus;

public class JobStatusStartedOperations extends JobStatusBaseOperations {

    @Override
    public JobStatus finish(Job job) {
        return JobStatus.FINISHED;
    }

    @Override
    public JobStatus dispute(Job job) {
        return JobStatus.DISPUTED;
    }

    @Override
    public JobStatus cancel(Job job) {
        return JobStatus.CANCELLED;
    }
}
