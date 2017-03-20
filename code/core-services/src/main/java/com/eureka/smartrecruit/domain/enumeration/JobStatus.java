package com.eureka.smartrecruit.domain.enumeration;

import com.eureka.smartrecruit.domain.Job;
import com.eureka.smartrecruit.domain.enumeration.operations.JobStatusApprovedOperations;
import com.eureka.smartrecruit.domain.enumeration.operations.JobStatusArchivedOperations;
import com.eureka.smartrecruit.domain.enumeration.operations.JobStatusCancelledOperations;
import com.eureka.smartrecruit.domain.enumeration.operations.JobStatusDisputedOperations;
import com.eureka.smartrecruit.domain.enumeration.operations.JobStatusExpiredOperations;
import com.eureka.smartrecruit.domain.enumeration.operations.JobStatusFeedbackOperations;
import com.eureka.smartrecruit.domain.enumeration.operations.JobStatusFinishedOperations;
import com.eureka.smartrecruit.domain.enumeration.operations.JobStatusOperations;
import com.eureka.smartrecruit.domain.enumeration.operations.JobStatusNewOperations;
import com.eureka.smartrecruit.domain.enumeration.operations.JobStatusRejectedOperations;
import com.eureka.smartrecruit.domain.enumeration.operations.JobStatusStartedOperations;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum JobStatus implements JobStatusOperations {

    NEW(new JobStatusNewOperations()),
    APPROVED(new JobStatusApprovedOperations()),
    REJECTED(new JobStatusRejectedOperations()),
    STARTED(new JobStatusStartedOperations()),
    FINISHED(new JobStatusFinishedOperations()),
    FEEDBACK(new JobStatusFeedbackOperations()),
    DISPUTED(new JobStatusDisputedOperations()),
    ARCHIVED(new JobStatusArchivedOperations()),
    EXPIRED(new JobStatusExpiredOperations()),
    CANCELLED(new JobStatusCancelledOperations());

    private final JobStatusOperations operations;

    @Override
    public JobStatus approve(Job job) {
        return operations.approve(job);
    }

    @Override
    public JobStatus reject(Job job) {
        return operations.reject(job);
    }

    @Override
    public JobStatus start(Job job) {
        return operations.start(job);
    }

    @Override
    public JobStatus finish(Job job) {
        return operations.finish(job);
    }

    @Override
    public JobStatus feedback(Job job) {
        return operations.feedback(job);
    }

    @Override
    public JobStatus dispute(Job job) {
        return operations.dispute(job);
    }

    @Override
    public JobStatus archive(Job job) {
        return operations.archive(job);
    }

    @Override
    public JobStatus expire(Job job) {
        return operations.expire(job);
    }

    @Override
    public JobStatus cancel(Job job) {
        return operations.cancel(job);
    }
}
