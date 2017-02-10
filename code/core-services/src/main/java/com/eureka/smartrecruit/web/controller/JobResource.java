package com.eureka.smartrecruit.web.controller;

import com.eureka.smartrecruit.domain.Category;
import com.eureka.smartrecruit.domain.Job;
import com.eureka.smartrecruit.domain.Skill;
import com.eureka.smartrecruit.dto.JobDto;
import com.eureka.smartrecruit.service.JobService;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.dozer.Mapper;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@RequestMapping("/jobs")
public class JobResource {

    private final JobService jobService;
    private final Mapper mapper;

    @RequestMapping(method=RequestMethod.POST, produces={ MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody JobDto jobDto) {
        Job job = mapper.map(jobDto, Job.class);
        jobService.create(job);
    }

    @RequestMapping(method=RequestMethod.PUT, produces={ MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody JobDto jobDto) {
        Job job = jobService.findById(jobDto.getId());
        job.setTitle(jobDto.getTitle());
        job.setDescription(jobDto.getDescription());
        job.setCategory(mapper.map(jobDto.getCategory(), Category.class));
        job.setRemuneration(jobDto.getRemuneration());
        job.setDuration(jobDto.getDuration());
        job.setNumberPositions(jobDto.getNumberPositions());
        job.setDeadline(jobDto.getDeadline());
        job.setSkills(jobDto.getSkills().stream().map(skill -> mapper.map(skill, Skill.class)).collect(Collectors.toList()));
        jobService.update(job);
    }

    @RequestMapping(value="/{id}/open", method=RequestMethod.PUT, produces={ MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public void openJob(@PathVariable("id") Long id) {
        Job job = jobService.findById(id);
        job.getStatus().open(job);
        jobService.update(job);
    }

    @RequestMapping(value="/{id}/negotiate", method=RequestMethod.PUT, produces={ MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public void negotiateJob(@PathVariable("id") Long id) {
        Job job = jobService.findById(id);
        job.getStatus().negotiate(job);
        jobService.update(job);
    }

    @RequestMapping(value="/{id}/start", method=RequestMethod.PUT, produces={ MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public void startJob(@PathVariable("id") Long id) {
        Job job = jobService.findById(id);
        job.getStatus().start(job);
        jobService.update(job);
    }

    @RequestMapping(value="/{id}/finish", method=RequestMethod.PUT, produces={ MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public void finishJob(@PathVariable("id") Long id) {
        Job job = jobService.findById(id);
        job.getStatus().finish(job);
        jobService.update(job);
    }

    @RequestMapping(value="/{id}/cancel", method=RequestMethod.PUT, produces={ MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public void cancelJob(@PathVariable("id") Long id) {
        Job job = jobService.findById(id);
        job.getStatus().cancel(job);
        jobService.update(job);
    }

    @RequestMapping(value="/{id}/dispute", method=RequestMethod.PUT, produces={ MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public void disputeJob(@PathVariable("id") Long id) {
        Job job = jobService.findById(id);
        job.getStatus().dispute(job);
        jobService.update(job);
    }

    @RequestMapping(value="/{id}/archive", method=RequestMethod.PUT, produces={ MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public void archiveJob(@PathVariable("id") Long id) {
        Job job = jobService.findById(id);
        job.getStatus().archive(job);
        jobService.update(job);
    }

    @RequestMapping(value="/{id}/expire", method=RequestMethod.PUT, produces={ MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public void expireJob(@PathVariable("id") Long id) {
        Job job = jobService.findById(id);
        job.getStatus().expire(job);
        jobService.update(job);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces={ MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        jobService.delete(id);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public JobDto findById(@PathVariable("id") Long id) {
        return mapper.map(jobService.findById(id), JobDto.class);
    }

    @RequestMapping(method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public List<JobDto> find(@QuerydslPredicate(root = Job.class) Predicate predicate) {
        return jobService.find(predicate).stream().map(job -> mapper.map(job, JobDto.class)).collect(Collectors.toList());
    }

    @RequestMapping(value="/self", method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public List<JobDto> findMyProjects() {
        return jobService.findMyJobs().stream().map(job -> mapper.map(job, JobDto.class)).collect(Collectors.toList());
    }
}
