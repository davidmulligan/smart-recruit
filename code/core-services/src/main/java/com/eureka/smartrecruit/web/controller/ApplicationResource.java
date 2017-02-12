package com.eureka.smartrecruit.web.controller;

import com.eureka.smartrecruit.domain.Application;
import com.eureka.smartrecruit.domain.Job;
import com.eureka.smartrecruit.dto.ApplicationDto;
import com.eureka.smartrecruit.service.ApplicationService;
import com.eureka.smartrecruit.service.JobService;
import lombok.RequiredArgsConstructor;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jobs/{jobId}/applications")
public class ApplicationResource {

    private final ApplicationService applicationService;
    private final JobService jobService;
    private final Mapper mapper;

    @RequestMapping(method=RequestMethod.POST, produces={ MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable("jobId") Long jobId, @RequestBody ApplicationDto applicationDto) {
        Job job = jobService.findById(jobId);
        Application application = mapper.map(applicationDto, Application.class);
        applicationService.create(application, job);
    }

    @RequestMapping(method=RequestMethod.PUT, produces={ MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody ApplicationDto applicationDto) {
        Application application = applicationService.findById(applicationDto.getId());
        applicationService.update(application);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces={ MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        applicationService.delete(id);
    }

    @RequestMapping(method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public List<ApplicationDto> findAll(@PathVariable("jobId") Long jobId) {
        Job job = jobService.findById(jobId);
        return job.getApplications().stream().map(application -> mapper.map(application, ApplicationDto.class)).collect(Collectors.toList());
    }

    @RequestMapping(value="/{id}/accept", method=RequestMethod.PUT, produces={ MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public void accept(@PathVariable("jobId") Long jobId, @PathVariable("id") Long id) {
        Application application = applicationService.findById(id);
        application.setAccepted(true);
        application.setAcceptedOn(LocalDateTime.now());
        Job job = jobService.findById(jobId);
        job.getStatus().start(job);
        jobService.update(job);
        applicationService.update(application);
    }
}
