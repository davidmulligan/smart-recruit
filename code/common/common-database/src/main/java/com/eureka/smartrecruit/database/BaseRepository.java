package com.eureka.smartrecruit.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T extends BaseDomainObject> extends CrudRepository<T, Long> {

    Optional<T> findById(Long id);
}
