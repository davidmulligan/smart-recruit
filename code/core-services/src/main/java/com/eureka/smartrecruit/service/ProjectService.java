package com.eureka.smartrecruit.service;

import com.eureka.smartrecruit.domain.Project;
import com.eureka.smartrecruit.microservice.exception.ResourceNotFoundException;
import com.eureka.smartrecruit.respository.ProjectRepository;
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
public class ProjectService {

    private final ProjectRepository projectRepository;

    public void create(final Project project) {
        projectRepository.save(project);
    }

    public void update(final Project project) {
        projectRepository.save(project);
    }

    public void delete(final Long id) {
        projectRepository.delete(id);
    }

    public Project findById(final Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Could not find project with id: %s", id)));
    }

    public List<Project> find(Predicate predicate) {
        return Seq.seq(projectRepository.findAll(predicate)).collect(Collectors.toList());
    }
}
