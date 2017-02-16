package com.eureka.smartrecruit.service;

import com.eureka.smartrecruit.domain.Skill;
import com.eureka.smartrecruit.microservice.exception.ResourceNotFoundException;
import com.eureka.smartrecruit.respository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;

    public void create(final Skill skill) {
        skillRepository.save(skill);
    }

    public void update(final Skill skill) {
        skillRepository.save(skill);
    }

    public void delete(final Skill skill) {
        skillRepository.delete(skill);
    }

    public Skill findById(final Long id) {
        return skillRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Could not find skill with id: %s", id)));
    }

    public List<Skill> findAll() {
        return skillRepository.findAllByOrderByNameAsc();
    }

    public List<Skill> findPrincipal() {
        return skillRepository.findPrincipalByOrderByNameAsc();
    }
}
