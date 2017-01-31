package com.eureka.smartrecruit.service;

import com.eureka.smartrecruit.domain.Job;
import com.eureka.smartrecruit.microservice.exception.ResourceNotFoundException;
import com.eureka.smartrecruit.respository.JobRepository;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.jooq.lambda.Seq;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    public void create(final Job job) {
        jobRepository.save(job);
    }

    public void update(final Job job) {
        jobRepository.save(job);
    }

    public void delete(final Long id) {
        jobRepository.delete(id);
    }

    public Job findById(final Long id) {
        return jobRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Could not find job with id: %s", id)));
    }

    public List<Job> find(Predicate predicate) {
        return Seq.seq(jobRepository.findAll(predicate)).collect(Collectors.toList());
    }
}
