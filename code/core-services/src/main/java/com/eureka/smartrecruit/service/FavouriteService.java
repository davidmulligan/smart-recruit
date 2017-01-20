package com.eureka.smartrecruit.service;

import com.eureka.smartrecruit.domain.Favourite;
import com.eureka.smartrecruit.domain.User;
import com.eureka.smartrecruit.microservice.exception.ResourceNotFoundException;
import com.eureka.smartrecruit.respository.FavouriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FavouriteService {

    private final FavouriteRepository favouriteRepository;

    public void create(final Favourite favourite, final User user) {
        favourite.setUser(user);
        favouriteRepository.save(favourite);
    }

    public void update(final Favourite favourite) {
        favouriteRepository.save(favourite);
    }

    public void delete(final Long id) {
        favouriteRepository.delete(id);
    }

    public Favourite findById(final Long id) {
        return favouriteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Could not find favourite with id: %s", id)));
    }
}
