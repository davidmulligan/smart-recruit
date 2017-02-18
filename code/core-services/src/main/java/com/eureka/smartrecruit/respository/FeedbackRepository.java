package com.eureka.smartrecruit.respository;

import com.eureka.smartrecruit.database.BaseRepository;
import com.eureka.smartrecruit.domain.Feedback;
import com.eureka.smartrecruit.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends BaseRepository<Feedback> {

    List<Feedback> findByUser(User user);
}
