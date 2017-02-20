package com.eureka.smartrecruit.respository;

import com.eureka.smartrecruit.domain.Dispute;
import com.eureka.smartrecruit.domain.QDispute;
import org.springframework.stereotype.Repository;

@Repository
public interface DisputeRepository extends QueryableRepository<Dispute, QDispute> {
}
