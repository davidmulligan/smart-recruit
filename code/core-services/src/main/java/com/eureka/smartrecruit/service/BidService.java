package com.eureka.smartrecruit.service;

import com.eureka.smartrecruit.domain.Bid;
import com.eureka.smartrecruit.microservice.exception.ResourceNotFoundException;
import com.eureka.smartrecruit.respository.BidRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BidService {

    private final BidRepository bidRepository;

    public void create(final Bid bid) {
        bidRepository.save(bid);
    }

    public void update(final Bid bid) {
        bidRepository.save(bid);
    }

    public void delete(final Long id) {
        bidRepository.delete(id);
    }

    public Bid findById(final Long id) {
        return bidRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Could not find bid with id: %s", id)));
    }
}
