package com.eureka.smartrecruit.domain.enumeration.operations;

import com.eureka.smartrecruit.domain.Job;
import com.eureka.smartrecruit.domain.enumeration.JobStatus;

public class JobStatusApprovedOperations extends JobStatusBaseOperations {

    @Override
    public JobStatus start(Job job) {
        if (job.isFixedPrice()) {
            return JobStatus.STARTED;
        } else {
            return super.start(job);
        }
    }

    @Override
    public JobStatus archive(Job job) {
        return JobStatus.ARCHIVED;
    }

    @Override
    public JobStatus expire(Job job) {
        return JobStatus.EXPIRED;
    }

    @Override
    public JobStatus cancel(Job job) {
        return JobStatus.CANCELLED;
    }
}
