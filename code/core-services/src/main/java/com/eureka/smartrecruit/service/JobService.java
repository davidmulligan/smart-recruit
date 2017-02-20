package com.eureka.smartrecruit.service;

import com.eureka.smartrecruit.domain.Bid;
import com.eureka.smartrecruit.domain.Job;
import com.eureka.smartrecruit.domain.Workroom;
import com.eureka.smartrecruit.domain.enumeration.BidStatus;
import com.eureka.smartrecruit.domain.enumeration.JobStatus;
import com.eureka.smartrecruit.domain.enumeration.UserType;
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
@Transactional
@RequiredArgsConstructor
public class JobService extends BaseService {

    private final JobRepository jobRepository;

    public void create(final Job job) {
        job.setStatus(JobStatus.NEW);
        job.setWorkroom(new Workroom());
        jobRepository.save(job);
    }

    public void update(final Job job) {
        jobRepository.save(job);
    }

    public void approve(final Job job) {
        job.setStatus(job.getStatus().approve(job));
        update(job);
    }

    public void reject(final Job job) {
        job.setStatus(job.getStatus().reject(job));
        update(job);
    }

    public void finish(final Job job) {
        job.setStatus(job.getStatus().finish(job));
        update(job);
    }

    public void dispute(final Job job) {
        job.setStatus(job.getStatus().dispute(job));
        update(job);
    }

    public void archive(final Job job) {
        job.setStatus(job.getStatus().archive(job));
        update(job);
    }

    public void cancel(final Job job) {
        job.setStatus(job.getStatus().cancel(job));
        update(job);
    }

    public void accept(final Job job, Bid bid) {
        bid.setStatus(BidStatus.ACCEPTED);
        job.setStatus(job.getStatus().start(job));
        update(job);
    }

    public Job findById(final Long id) {
        return jobRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Could not find job with id: %s", id)));
    }

    public List<Job> findMyJobs() {
        if (getCurrentUser().getType() == UserType.CLIENT) {
            return Seq.seq(jobRepository.findAll(job.createdBy.eq(getCurrentUser()), job.createdOn.desc())).toList();
        } else {
            return Seq.seq(jobRepository.findAll(job.bids.any().createdBy.eq(getCurrentUser()).and(job.bids.any().status.eq(BidStatus.ACCEPTED)), job.createdOn.desc())).toList();
        }
    }

    public List<Job> find(Predicate predicate) {
        return Seq.seq(jobRepository.findAll(predicate)).toList();
    }
}
