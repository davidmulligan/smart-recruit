package com.eureka.smartrecruit.respository;

import com.eureka.smartrecruit.domain.Job;
import com.eureka.smartrecruit.domain.QJob;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends QueryableRepository<Job, QJob> {

    @Override
    default void customize(QuerydslBindings bindings, QJob job) {
        bindings.bind(job.title).first((path, value) -> path.containsIgnoreCase(value));
    }
}
