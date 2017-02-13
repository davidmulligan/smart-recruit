package com.eureka.smartrecruit.domain.enumeration.operations;

import com.eureka.smartrecruit.domain.Job;
import com.eureka.smartrecruit.domain.enumeration.JobStatus;

public abstract class JobStatusBaseOperations implements JobStatusOperations {

    @Override
    public JobStatus approve(final Job job) {
        throw new UnsupportedJobStatusTransitionException(job.getStatus(), JobStatus.APPROVED);
    }

    @Override
    public JobStatus reject(final Job job) {
        throw new UnsupportedJobStatusTransitionException(job.getStatus(), JobStatus.REJECTED);
    }

    @Override
    public JobStatus start(final Job job) {
        throw new UnsupportedJobStatusTransitionException(job.getStatus(), JobStatus.STARTED);
    }

    @Override
    public JobStatus finish(final Job job) {
        throw new UnsupportedJobStatusTransitionException(job.getStatus(), JobStatus.FINISHED);
    }

    @Override
    public JobStatus dispute(final Job job) {
        throw new UnsupportedJobStatusTransitionException(job.getStatus(), JobStatus.DISPUTED);
    }

    @Override
    public JobStatus archive(final Job job) {
        throw new UnsupportedJobStatusTransitionException(job.getStatus(), JobStatus.ARCHIVED);
    }

    @Override
    public JobStatus expire(final Job job) {
        throw new UnsupportedJobStatusTransitionException(job.getStatus(), JobStatus.EXPIRED);
    }

    @Override
    public JobStatus cancel(final Job job) {
        throw new UnsupportedJobStatusTransitionException(job.getStatus(), JobStatus.CANCELLED);
    }
}
