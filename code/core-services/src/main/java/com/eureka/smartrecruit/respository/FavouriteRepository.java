package com.eureka.smartrecruit.respository;

import com.eureka.smartrecruit.domain.Favourite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavouriteRepository extends CrudRepository<Favourite, Long> {

    Optional<Favourite> findById(Long id);
}
