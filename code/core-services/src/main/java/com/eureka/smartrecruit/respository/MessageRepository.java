package com.eureka.smartrecruit.respository;

import com.eureka.smartrecruit.domain.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

    Optional<Message> findById(Long id);
}
