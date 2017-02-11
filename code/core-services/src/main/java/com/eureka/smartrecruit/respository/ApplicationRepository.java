package com.eureka.smartrecruit.respository;

import com.eureka.smartrecruit.domain.Application;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationRepository extends CrudRepository<Application, Long> {

    Optional<Application> findById(Long id);
}
