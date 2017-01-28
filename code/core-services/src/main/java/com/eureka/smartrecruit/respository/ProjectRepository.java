package com.eureka.smartrecruit.respository;

import com.eureka.smartrecruit.domain.Project;
import com.eureka.smartrecruit.domain.QProject;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long>, QueryDslPredicateExecutor<Project>, QuerydslBinderCustomizer<QProject> {

    Optional<Project> findById(Long id);

    @Override
    default void customize(QuerydslBindings bindings, QProject project) {
        bindings.bind(project.title).first((path, value) -> path.containsIgnoreCase(value));
    }
}
