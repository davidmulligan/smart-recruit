package com.eureka.smartrecruit.service;

import com.eureka.smartrecruit.domain.Dispute;
import com.eureka.smartrecruit.microservice.exception.ResourceNotFoundException;
import com.eureka.smartrecruit.respository.DisputeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DisputeService {

    private final DisputeRepository disputeRepository;

    public void create(final Dispute dispute) {
        disputeRepository.save(dispute);
    }

    public void update(final Dispute dispute) {
        disputeRepository.save(dispute);
    }

    public void delete(final Long id) {
        disputeRepository.delete(id);
    }

    public Dispute findById(final Long id) {
        return disputeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Could not find dispute with id: %s", id)));
    }
}
