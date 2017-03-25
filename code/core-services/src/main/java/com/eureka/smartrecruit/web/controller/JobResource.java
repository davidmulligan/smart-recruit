package com.eureka.smartrecruit.web.controller;

import com.eureka.smartrecruit.domain.Category;
import com.eureka.smartrecruit.domain.Job;
import com.eureka.smartrecruit.domain.Skill;
import com.eureka.smartrecruit.domain.User;
import com.eureka.smartrecruit.dto.JobDto;
import com.eureka.smartrecruit.service.JobService;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.dozer.Mapper;
import org.jooq.lambda.Seq;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @RequestMapping(method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody JobDto jobDto) {
        jobService.create(mapper.map(jobDto, Job.class));
    }

    @RequestMapping(method=RequestMethod.PUT)
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
        job.setSkills(Seq.seq(jobDto.getSkills()).map(skill -> mapper.map(skill, Skill.class)).toList());
        jobService.update(job);
    }

    @RequestMapping(value="/{id}/approve", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void approve(@PathVariable("id") Long id) {
        jobService.approve(jobService.findById(id));
    }

    @RequestMapping(value="/{id}/reject", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void reject(@PathVariable("id") Long id) {
        jobService.reject(jobService.findById(id));
    }

    @RequestMapping(value="/{id}/finish", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void finish(@PathVariable("id") Long id) {
        jobService.finish(jobService.findById(id));
    }

    @RequestMapping(value="/{id}/dispute", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void dispute(@PathVariable("id") Long id) {
        jobService.dispute(jobService.findById(id));
    }

    @RequestMapping(value="/{id}/archive", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void archive(@PathVariable("id") Long id) {
        jobService.archive(jobService.findById(id));
    }

    @RequestMapping(value="/{id}/cancel", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void cancel(@PathVariable("id") Long id) {
        jobService.cancel(jobService.findById(id));
    }

    @RequestMapping(value="/self", method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public List<JobDto> findMyJobs() {
        return Seq.seq(jobService.findMyJobs()).map(job -> {
            JobDto jobDto = mapper.map(job, JobDto.class);
            jobDto.setBids(Seq.seq(jobDto.getBids())
                    .filter(bid -> bid.getCreatedBy().getId().equals(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()))
                    .collect(Collectors.toList()));
            return jobDto;
        }).toList();
    }

    @RequestMapping(method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public List<JobDto> find(@QuerydslPredicate(root = Job.class) Predicate predicate) {
        return Seq.seq(jobService.find(predicate)).map(job -> mapper.map(job, JobDto.class)).toList();
    }
}
