package com.eureka.smartrecruit.respository;

import com.eureka.smartrecruit.database.BaseRepository;
import com.eureka.smartrecruit.domain.Message;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends BaseRepository<Message> {
}
