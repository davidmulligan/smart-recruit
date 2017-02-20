package com.eureka.smartrecruit.service;

import com.eureka.smartrecruit.domain.Bid;
import com.eureka.smartrecruit.domain.enumeration.BidStatus;
import com.eureka.smartrecruit.microservice.exception.ResourceNotFoundException;
import com.eureka.smartrecruit.respository.BidRepository;
import lombok.RequiredArgsConstructor;
import org.jooq.lambda.Seq;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.eureka.smartrecruit.domain.QBid.bid;

@Service
@Transactional
@RequiredArgsConstructor
public class BidService extends BaseService {

    private final BidRepository bidRepository;

    public void create(final Bid bid) {
        bid.setStatus(BidStatus.PENDING);
        bidRepository.save(bid);
    }

    public void update(final Bid bid) {
        bidRepository.save(bid);
    }

    public Bid findById(final Long id) {
        return bidRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Could not find bid with id: %s", id)));
    }

    public List<Bid> findMyBids() {
        return Seq.seq(bidRepository.findAll(bid.createdBy.eq(getCurrentUser()), bid.createdOn.desc())).toList();
    }
}
