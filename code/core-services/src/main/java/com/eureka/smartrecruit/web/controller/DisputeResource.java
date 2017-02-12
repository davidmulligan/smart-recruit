package com.eureka.smartrecruit.web.controller;

import com.eureka.smartrecruit.domain.Dispute;
import com.eureka.smartrecruit.domain.Job;
import com.eureka.smartrecruit.dto.DisputeDto;
import com.eureka.smartrecruit.service.DisputeService;
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

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jobs/{jobId}/disputes")
public class DisputeResource {

    private final DisputeService disputeService;
    private final JobService jobService;
    private final Mapper mapper;

    @RequestMapping(method=RequestMethod.POST, produces={ MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable("jobId") Long jobId, @RequestBody DisputeDto disputeDto) {
        Job job = jobService.findById(jobId);
        Dispute dispute = mapper.map(disputeDto, Dispute.class);
        disputeService.create(dispute, job);
    }

    @RequestMapping(method=RequestMethod.PUT, produces={ MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody DisputeDto disputeDto) {
        Dispute dispute = disputeService.findById(disputeDto.getId());
        disputeService.update(dispute);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces={ MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        disputeService.delete(id);
    }

    @RequestMapping(method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public List<DisputeDto> findAll(@PathVariable("jobId") Long jobId) {
        Job job = jobService.findById(jobId);
        return job.getDisputes().stream().map(dispute -> mapper.map(dispute, DisputeDto.class)).collect(Collectors.toList());
    }
}
