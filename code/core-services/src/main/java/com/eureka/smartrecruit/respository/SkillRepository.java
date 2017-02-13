package com.eureka.smartrecruit.respository;

import com.eureka.smartrecruit.database.BaseRepository;
import com.eureka.smartrecruit.domain.Skill;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends BaseRepository<Skill> {

    List<Skill> findAllByOrderByNameAsc();

    List<Skill> findPrincipalByOrderByNameAsc();
}
