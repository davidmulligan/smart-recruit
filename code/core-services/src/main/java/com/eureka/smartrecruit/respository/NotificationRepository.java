package com.eureka.smartrecruit.respository;

import com.eureka.smartrecruit.domain.Notification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Long> {

    Optional<Notification> findById(Long id);
}
