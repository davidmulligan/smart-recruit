package com.eureka.smartrecruit.respository;

import com.eureka.smartrecruit.domain.Notification;
import com.eureka.smartrecruit.domain.QNotification;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends QueryableRepository<Notification, QNotification> {
}
