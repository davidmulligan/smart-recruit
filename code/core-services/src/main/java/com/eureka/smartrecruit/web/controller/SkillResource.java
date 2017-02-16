package com.eureka.smartrecruit.web.controller;

import com.eureka.smartrecruit.domain.Skill;
import com.eureka.smartrecruit.dto.SkillDto;
import com.eureka.smartrecruit.service.SkillService;
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
@RequestMapping(value="/skills")
public class SkillResource {

    private final SkillService skillService;
    private final Mapper mapper;

    @RequestMapping(method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody SkillDto skillDto) {
        skillService.create(mapper.map(skillDto, Skill.class));
    }

    @RequestMapping(method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody SkillDto skillDto) {
        Skill skill = skillService.findById(skillDto.getId());
        skill.setName(skillDto.getName());
        skill.setDescription(skillDto.getDescription());
        skill.setPrincipal(skillDto.isPrincipal());
        skill.setActive(skillDto.isActive());
        skillService.update(skill);
    }

    @RequestMapping(value="/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        skillService.delete(skillService.findById(id));
    }

    @RequestMapping(method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public List<SkillDto> findAll() {
        return Seq.seq(skillService.findAll()).map(skill -> mapper.map(skill, SkillDto.class)).toList();
    }

    @RequestMapping(value="/principal", method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public List<SkillDto> findPrincipal() {
        return Seq.seq(skillService.findPrincipal()).map(skill -> mapper.map(skill, SkillDto.class)).toList();
    }
}
