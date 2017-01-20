package com.eureka.smartrecruit.web.controller;

import com.eureka.smartrecruit.domain.Category;
import com.eureka.smartrecruit.domain.Project;
import com.eureka.smartrecruit.domain.Skill;
import com.eureka.smartrecruit.dto.ProjectDto;
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
public class ProjectRestResource {

    private final ProjectService projectService;
    private final Mapper mapper;

    @RequestMapping(value="/projects", method=RequestMethod.POST, produces={ "application/json" })
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody ProjectDto projectDto) {
        Project project = mapper.map(projectDto, Project.class);
        projectService.create(project);
    }

    @RequestMapping(value="/projects", method=RequestMethod.PUT, produces={ "application/json" })
    @ResponseStatus( HttpStatus.OK )
    public void update(@RequestBody ProjectDto projectDto) {
        Project project = projectService.findById(projectDto.getId());
        project.setTitle(projectDto.getTitle());
        project.setDescription(projectDto.getDescription());
        project.setActive(projectDto.isActive());
        project.setCategory(mapper.map(projectDto.getCategoryDto(), Category.class));
        project.setSkills(projectDto.getSkills().stream().map(skill -> mapper.map(skill, Skill.class)).collect(Collectors.toList()));
        projectService.update(project);
    }

    @RequestMapping(value="/projects/{id}", method=RequestMethod.DELETE, produces={ "application/json" })
    @ResponseStatus( HttpStatus.OK )
    public void delete(@PathVariable("id") Long id) {
        projectService.delete(id);
    }

    @RequestMapping(value="/projects", method=RequestMethod.GET, produces={ "application/json" })
    public List<ProjectDto> findAll() {
        return projectService.findAll().stream().map(project -> mapper.map(project, ProjectDto.class)).collect(Collectors.toList());
    }
}
