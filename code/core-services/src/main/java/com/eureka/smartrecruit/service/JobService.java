package com.eureka.smartrecruit.service;

import com.eureka.smartrecruit.domain.Job;
import com.eureka.smartrecruit.domain.Message;
import com.eureka.smartrecruit.domain.Notification;
import com.eureka.smartrecruit.domain.enumeration.JobStatus;
import com.eureka.smartrecruit.domain.enumeration.MessageType;
import com.eureka.smartrecruit.domain.enumeration.NotificationType;
import com.eureka.smartrecruit.microservice.exception.ResourceNotFoundException;
import com.eureka.smartrecruit.respository.JobRepository;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.jooq.lambda.Seq;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.eureka.smartrecruit.domain.QJob.job;

@Service
@RequiredArgsConstructor
public class JobService extends BaseService {

    private final JobRepository jobRepository;
    private final NotificationService notificationService;
    private final MessageService messageService;

    @Transactional
    public void create(final Job job) {
        job.setStatus(JobStatus.NEW);
        jobRepository.save(job);
    }

    @Transactional
    public void update(final Job job) {
        jobRepository.save(job);
    }

    @Transactional
    public void approve(final Job job) {
        job.setStatus(job.getStatus().approve(job));
        update(job);
        notificationService.create(new Notification(job, NotificationType.JOB_APPROVED));
        messageService.create(new Message(job, MessageType.MESSAGE1));
    }

    @Transactional
    public void reject(final Job job) {
        job.setStatus(job.getStatus().reject(job));
        update(job);
        notificationService.create(new Notification(job, NotificationType.JOB_REJECTED));
    }

    public void start(final Job job) {
        job.setStatus(job.getStatus().start(job));
        update(job);
        notificationService.create(new Notification(job, NotificationType.JOB_STARTED));
    }

    public void finish(final Job job) {
        job.setStatus(job.getStatus().finish(job));
        update(job);
        notificationService.create(new Notification(job, NotificationType.JOB_FINISHED));
    }

    public void feedback(final Job job) {
        job.setStatus(job.getStatus().feedback(job));
        update(job);
    }

    public void archive(final Job job) {
        job.setStatus(job.getStatus().archive(job));
        update(job);
        notificationService.create(new Notification(job, NotificationType.JOB_ARCHIVED));
    }

    public void cancel(final Job job) {
        job.setStatus(job.getStatus().cancel(job));
        update(job);
        notificationService.create(new Notification(job, NotificationType.JOB_CANCELLED));
    }

    public void dispute(final Job job) {
        job.setStatus(job.getStatus().dispute(job));
        update(job);
    }

    public Job findById(final Long id) {
        return jobRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Could not find job with id: %s", id)));
    }

    public List<Job> findMyJobs() {
        if (getCurrentUser().isClient()) {
            return Seq.seq(jobRepository.findAll(job.createdBy.eq(getCurrentUser()), job.createdOn.desc())).toList();
        } else {
            return Seq.seq(jobRepository.findAll(job.bids.any().createdBy.eq(getCurrentUser()), job.createdOn.desc())).toList();
        }
    }

    public List<Job> find(Predicate predicate) {
        return Seq.seq(jobRepository.findAll(predicate)).toList();
    }
}
