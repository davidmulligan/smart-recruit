package com.eureka.smartrecruit.respository;

import com.eureka.smartrecruit.domain.Dispute;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DisputeRepository extends CrudRepository<Dispute, Long> {

    Optional<Dispute> findById(Long id);
}
