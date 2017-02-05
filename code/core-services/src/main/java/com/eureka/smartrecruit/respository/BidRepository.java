package com.eureka.smartrecruit.respository;

import com.eureka.smartrecruit.domain.Bid;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BidRepository extends CrudRepository<Bid, Long> {

    Optional<Bid> findById(Long id);
}
