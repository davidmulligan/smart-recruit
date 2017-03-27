package com.eureka.smartrecruit.service;

import com.eureka.smartrecruit.domain.Membership;
import com.eureka.smartrecruit.microservice.exception.ResourceNotFoundException;
import com.eureka.smartrecruit.respository.MembershipRepository;
import lombok.RequiredArgsConstructor;
import org.jooq.lambda.Seq;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.eureka.smartrecruit.domain.QMembership.membership;

@Service
@RequiredArgsConstructor
public class MembershipService {

    private final MembershipRepository membershipRepository;

    @Transactional
    public void create(final Membership membership) {
        membershipRepository.save(membership);
    }

    @Transactional
    public void update(final Membership membership) {
        membershipRepository.save(membership);
    }

    @Transactional
    public void delete(final Long id) {
        membershipRepository.delete(id);
    }

    public Membership findById(final Long id) {
        return membershipRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Could not find membership with id: %s", id)));
    }

    public List<Membership> findAll() {
        return Seq.seq(membershipRepository.findAll(membership.name.asc())).toList();
    }
}
