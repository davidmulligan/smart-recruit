package com.eureka.smartrecruit.respository;

import com.eureka.smartrecruit.domain.Feedback;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeedbackRepository extends CrudRepository<Feedback, Long> {

    Optional<Feedback> findById(Long id);
}
