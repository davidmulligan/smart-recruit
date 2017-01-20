package com.eureka.smartrecruit.respository;

import com.eureka.smartrecruit.domain.Skill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkillRepository extends CrudRepository<Skill, Long> {

    Optional<Skill> findById(Long id);

    List<Skill> findAllByOrderByNameAsc();
}
