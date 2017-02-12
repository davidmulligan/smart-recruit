package com.eureka.smartrecruit.web.controller;

import com.eureka.smartrecruit.domain.Bid;
import com.eureka.smartrecruit.domain.Job;
import com.eureka.smartrecruit.dto.BidDto;
import com.eureka.smartrecruit.service.BidService;
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
@RequestMapping("/jobs/{jobId}/bids")
public class BidResource {

    private final BidService bidService;
    private final JobService jobService;
    private final Mapper mapper;

    @RequestMapping(method=RequestMethod.POST, produces={ MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable("jobId") Long jobId, @RequestBody BidDto bidDto) {
        Job job = jobService.findById(jobId);
        Bid bid = mapper.map(bidDto, Bid.class);
        bidService.create(bid, job);
    }

    @RequestMapping(method=RequestMethod.PUT, produces={ MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody BidDto bidDto) {
        Bid bid = bidService.findById(bidDto.getId());
        bidService.update(bid);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces={ MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        bidService.delete(id);
    }

    @RequestMapping(method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public List<BidDto> findAll(@PathVariable("jobId") Long jobId) {
        Job job = jobService.findById(jobId);
        return job.getBids().stream().map(bid -> mapper.map(bid, BidDto.class)).collect(Collectors.toList());
    }

    @RequestMapping(value="/{id}/accept", method=RequestMethod.PUT, produces={ MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public void accept(@PathVariable("jobId") Long jobId, @PathVariable("id") Long id) {
        Bid bid = bidService.findById(id);
        bid.setAccepted(true);
        bid.setAcceptedOn(LocalDateTime.now());
        bidService.update(bid);
        Job job = jobService.findById(jobId);
        job.getStatus().start(job);
        jobService.update(job);
    }
}
