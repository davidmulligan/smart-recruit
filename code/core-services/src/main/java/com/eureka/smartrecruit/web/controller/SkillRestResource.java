package com.eureka.smartrecruit.web.controller;

import com.eureka.smartrecruit.domain.Skill;
import com.eureka.smartrecruit.dto.SkillDto;
import com.eureka.smartrecruit.service.SkillService;
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
public class SkillRestResource {

    private final SkillService skillService;
    private final Mapper mapper;

    @RequestMapping(value="/skills", method=RequestMethod.POST, produces={ "application/json" })
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody SkillDto skillDto) {
        Skill skill = mapper.map(skillDto, Skill.class);
        skillService.create(skill);
    }

    @RequestMapping(value="/skills", method=RequestMethod.PUT, produces={ "application/json" })
    @ResponseStatus( HttpStatus.OK )
    public void update(@RequestBody SkillDto skillDto) {
        Skill skill = skillService.findById(skillDto.getId());
        skill.setName(skillDto.getName());
        skill.setDescription(skillDto.getDescription());
        skill.setActive(skillDto.isActive());
        skillService.update(skill);
    }

    @RequestMapping(value="/skills/{id}", method=RequestMethod.DELETE, produces={ "application/json" })
    @ResponseStatus( HttpStatus.OK )
    public void delete(@PathVariable("id") Long id) {
        skillService.delete(skillService.findById(id));
    }

    @RequestMapping(value="/skills", method=RequestMethod.GET, produces={ "application/json" })
    public List<SkillDto> findAll() {
        return skillService.findAll().stream().map(skill -> mapper.map(skill, SkillDto.class)).collect(Collectors.toList());
    }
}
