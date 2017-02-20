package com.eureka.smartrecruit.respository;

import com.eureka.smartrecruit.domain.QSkill;
import com.eureka.smartrecruit.domain.Skill;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends QueryableRepository<Skill, QSkill> {
}
