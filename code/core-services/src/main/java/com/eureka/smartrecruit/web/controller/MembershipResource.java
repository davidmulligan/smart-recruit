package com.eureka.smartrecruit.web.controller;

import com.eureka.smartrecruit.domain.Membership;
import com.eureka.smartrecruit.dto.MembershipDto;
import com.eureka.smartrecruit.service.MembershipService;
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
@RequestMapping("/memberships")
public class MembershipResource {

    private final MembershipService membershipService;
    private final Mapper mapper;

    @RequestMapping(method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody MembershipDto membershipDto) {
        membershipService.create(mapper.map(membershipDto, Membership.class));
    }

    @RequestMapping(method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody MembershipDto membershipDto) {
        Membership membership = membershipService.findById(membershipDto.getId());
        membership.setName(membershipDto.getName());
        membership.setDescription(membershipDto.getDescription());
        membership.setCost(membershipDto.getCost());
        membership.setActive(membershipDto.isActive());
        membershipService.update(membership);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        membershipService.delete(id);
    }

    @RequestMapping(method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public List<MembershipDto> findAll() {
        return Seq.seq(membershipService.findAll()).map(membership -> mapper.map(membership, MembershipDto.class)).toList();
    }
}
