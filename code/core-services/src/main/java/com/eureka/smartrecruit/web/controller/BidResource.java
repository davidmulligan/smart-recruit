package com.eureka.smartrecruit.web.controller;

import com.eureka.smartrecruit.domain.Bid;
import com.eureka.smartrecruit.dto.BidDto;
import com.eureka.smartrecruit.service.BidService;
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
@RequestMapping({"/jobs/{jobId}/bids", "/bids"})
public class BidResource {

    private final BidService bidService;
    private final JobService jobService;
    private final Mapper mapper;

    @RequestMapping(method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable("jobId") Long jobId, @RequestBody BidDto bidDto) {
        Bid bid = mapper.map(bidDto, Bid.class);
        bid.setJob(jobService.findById(jobId));
        bidService.create(bid);
    }

    @RequestMapping(method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody BidDto bidDto) {
        Bid bid = bidService.findById(bidDto.getId());
        bid.setComment(bidDto.getComment());
        bid.setQuote(bidDto.getQuote());
        bidService.update(bid);
    }

    @RequestMapping(value="/{id}/accept", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void accept(@PathVariable("id") Long id) {
        bidService.accept(bidService.findById(id));
    }

    @RequestMapping(value="/{id}/reject", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void reject(@PathVariable("id") Long id) {
        bidService.reject(bidService.findById(id));
    }

    @RequestMapping(value="/{id}/confirm", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void confirm(@PathVariable("id") Long id) {
        bidService.confirm(bidService.findById(id));
    }

    @RequestMapping(value="/{id}/cancel", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void cancel(@PathVariable("id") Long id) {
        bidService.cancel(bidService.findById(id));
    }

    @RequestMapping(value="/self", method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public List<BidDto> findMyBids() {
        return Seq.seq(bidService.findMyBids()).map(bid -> mapper.map(bid, BidDto.class)).toList();
    }
}
