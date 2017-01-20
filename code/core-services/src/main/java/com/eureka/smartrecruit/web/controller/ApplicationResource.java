package com.eureka.smartrecruit.web.controller;

import com.eureka.smartrecruit.domain.Application;
import com.eureka.smartrecruit.domain.Project;
import com.eureka.smartrecruit.dto.ApplicationDto;
import com.eureka.smartrecruit.service.ApplicationService;
import com.eureka.smartrecruit.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects/{projectId}")
public class ApplicationResource {

    private final ApplicationService applicationService;
    private final ProjectService projectService;
    private final Mapper mapper;

    @RequestMapping(value="/applications", method= RequestMethod.POST, produces={ "application/json" })
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable("projectId") Long projectId, @RequestBody ApplicationDto applicantDto) {
        Project project = projectService.findById(projectId);
        Application application = mapper.map(applicantDto, Application.class);
        applicationService.create(application, project);
    }

    @RequestMapping(value="/applications", method=RequestMethod.PUT, produces={ "application/json" })
    @ResponseStatus( HttpStatus.OK )
    public void update(@RequestBody ApplicationDto applicationDto) {
        Application application = applicationService.findById(applicationDto.getId());
        applicationService.update(application);
    }

    @RequestMapping(value="/applications/{id}", method=RequestMethod.DELETE, produces={ "application/json" })
    @ResponseStatus( HttpStatus.OK )
    public void delete(@PathVariable("id") Long id) {
        applicationService.delete(id);
    }

    @RequestMapping(value="/applications", method=RequestMethod.GET, produces={ "application/json" })
    public List<ApplicationDto> findAll(@PathVariable("projectId") Long projectId) {
        Project project = projectService.findById(projectId);
        return project.getApplications().stream().map(application -> mapper.map(application, ApplicationDto.class)).collect(Collectors.toList());
    }
}
