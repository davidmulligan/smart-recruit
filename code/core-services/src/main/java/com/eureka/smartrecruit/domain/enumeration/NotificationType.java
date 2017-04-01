package com.eureka.smartrecruit.domain.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum NotificationType {

    JOB_APPROVED("Your job has been approved", "Your job has been approved", "job_approved"),
    JOB_REJECTED("Your job has been rejected", "Your job has been rejected", "job_rejected"),
    JOB_STARTED("Your job has been started", "Your job has been started", "job_started"),
    JOB_FINISHED("Your job has been finished", "Your job has been finished", "job_finished"),
    JOB_ARCHIVED("Your job has been archived", "Your job has been archived", "job_archived"),
    JOB_CANCELLED("Your job has been cancelled", "Your job has been cancelled", "job_cancelled");

    private final String subject;
    private final String content;
    private final String emailTemplate;
}