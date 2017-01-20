package com.eureka.smartrecruit.respository;

import com.eureka.smartrecruit.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    Optional<Project> findById(Long id);

    List<Project> findAllByOrderByTitleAsc();
}
