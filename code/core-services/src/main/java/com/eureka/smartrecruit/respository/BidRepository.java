package com.eureka.smartrecruit.respository;

import com.eureka.smartrecruit.domain.Bid;
import com.eureka.smartrecruit.domain.QBid;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepository extends QueryableRepository<Bid, QBid> {
}
