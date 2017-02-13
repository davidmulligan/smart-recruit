package com.eureka.smartrecruit.web.controller;

import com.eureka.smartrecruit.domain.Application;
import com.eureka.smartrecruit.domain.Job;
import com.eureka.smartrecruit.dto.ApplicationDto;
import com.eureka.smartrecruit.service.ApplicationService;
import com.eureka.smartrecruit.service.JobService;
import lombok.RequiredArgsConstructor;
import org.dozer.Mapper;
import org.jooq.lambda.Seq;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jobs/{jobId}/applications")
public class ApplicationResource {

    private final ApplicationService applicationService;
    private final JobService jobService;
    private final Mapper mapper;

    @RequestMapping(method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable("jobId") Long jobId, @RequestBody ApplicationDto applicationDto) {
        Application application = mapper.map(applicationDto, Application.class);
        application.setJob(jobService.findById(jobId));
        applicationService.create(application);
    }

    @RequestMapping(method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody ApplicationDto applicationDto) {
        Application application = applicationService.findById(applicationDto.getId());
        application.setComment(applicationDto.getComment());
        applicationService.update(application);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        applicationService.delete(id);
    }

    @RequestMapping(value="/{id}/accept", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void accept(@PathVariable("jobId") Long jobId, @PathVariable("id") Long id) {
        Job job = jobService.findById(jobId);
        Application application = applicationService.findById(id);
        jobService.accept(job, application);
    }

    @RequestMapping(method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public List<ApplicationDto> findAll(@PathVariable("jobId") Long jobId) {
        return Seq.seq(jobService.findById(jobId).getApplications()).map(application -> mapper.map(application, ApplicationDto.class)).toList();
    }
}
