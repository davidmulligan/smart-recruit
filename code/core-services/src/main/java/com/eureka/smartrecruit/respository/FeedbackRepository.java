package com.eureka.smartrecruit.respository;

import com.eureka.smartrecruit.domain.Feedback;
import com.eureka.smartrecruit.domain.QFeedback;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends QueryableRepository<Feedback, QFeedback> {
}
