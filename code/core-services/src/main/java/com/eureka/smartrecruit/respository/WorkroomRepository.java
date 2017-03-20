package com.eureka.smartrecruit.respository;

import com.eureka.smartrecruit.domain.QWorkroom;
import com.eureka.smartrecruit.domain.Workroom;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkroomRepository extends QueryableRepository<Workroom, QWorkroom> {
}
