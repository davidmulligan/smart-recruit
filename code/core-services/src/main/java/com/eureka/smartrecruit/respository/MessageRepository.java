package com.eureka.smartrecruit.respository;

import com.eureka.smartrecruit.domain.Message;
import com.eureka.smartrecruit.domain.QMessage;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends QueryableRepository<Message, QMessage> {
}
