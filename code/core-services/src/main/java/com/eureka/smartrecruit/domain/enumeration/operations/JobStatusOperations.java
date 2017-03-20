package com.eureka.smartrecruit.domain.enumeration.operations;

import com.eureka.smartrecruit.domain.Job;
import com.eureka.smartrecruit.domain.enumeration.JobStatus;

public interface JobStatusOperations {

    JobStatus approve(final Job job);

    JobStatus reject(final Job job);

    JobStatus start(final Job job);

    JobStatus finish(final Job job);

    JobStatus feedback(final Job job);

    JobStatus dispute(final Job job);

    JobStatus archive(final Job job);

    JobStatus expire(final Job job);

    JobStatus cancel(final Job job);
}
