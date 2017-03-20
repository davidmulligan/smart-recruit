package com.eureka.smartrecruit.respository;

import com.eureka.smartrecruit.domain.Job;
import com.eureka.smartrecruit.domain.QJob;
import com.eureka.smartrecruit.domain.User;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends QueryableRepository<Job, QJob> {

    List<Job> findByCreatedBy(User user);

    @Override
    default void customize(QuerydslBindings bindings, QJob job) {
        bindings.bind(job.title).first((path, value) -> path.containsIgnoreCase(value));
    }
}
