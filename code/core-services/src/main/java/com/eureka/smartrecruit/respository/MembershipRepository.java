package com.eureka.smartrecruit.respository;

import com.eureka.smartrecruit.domain.Membership;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MembershipRepository extends CrudRepository<Membership, Long> {

    Optional<Membership> findById(Long id);

    List<Membership> findAllByOrderByNameAsc();
}
