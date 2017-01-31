package com.eureka.smartrecruit.respository;

import com.eureka.smartrecruit.domain.Job;
import com.eureka.smartrecruit.domain.QJob;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobRepository extends CrudRepository<Job, Long>, QueryDslPredicateExecutor<Job>, QuerydslBinderCustomizer<QJob> {

    Optional<Job> findById(Long id);

    @Override
    default void customize(QuerydslBindings bindings, QJob job) {
        bindings.bind(job.title).first((path, value) -> path.containsIgnoreCase(value));
    }
}
