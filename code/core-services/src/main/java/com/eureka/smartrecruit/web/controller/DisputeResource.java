package com.eureka.smartrecruit.web.controller;

import com.eureka.smartrecruit.domain.Dispute;
import com.eureka.smartrecruit.dto.DisputeDto;
import com.eureka.smartrecruit.service.DisputeService;
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
@RequestMapping("/jobs/{jobId}/disputes")
public class DisputeResource {

    private final DisputeService disputeService;
    private final JobService jobService;
    private final Mapper mapper;

    @RequestMapping(method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable("jobId") Long jobId, @RequestBody DisputeDto disputeDto) {
        Dispute dispute = mapper.map(disputeDto, Dispute.class);
        dispute.setJob(jobService.findById(jobId));
        disputeService.create(dispute);
    }

    @RequestMapping(method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody DisputeDto disputeDto) {
        Dispute dispute = disputeService.findById(disputeDto.getId());
        dispute.setSubject(disputeDto.getSubject());
        dispute.setComplaint(disputeDto.getComplaint());
        dispute.setReply(disputeDto.getReply());
        dispute.setAdminReply(disputeDto.getAdminReply());
        dispute.setResolved(disputeDto.isResolved());
        disputeService.update(dispute);
    }

    @RequestMapping(method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public List<DisputeDto> findAll(@PathVariable("jobId") Long jobId) {
        return Seq.seq(jobService.findById(jobId).getDisputes()).map(dispute -> mapper.map(dispute, DisputeDto.class)).toList();
    }
}
