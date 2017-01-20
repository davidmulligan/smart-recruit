package com.eureka.smartrecruit.web.controller;

import com.eureka.smartrecruit.domain.Favourite;
import com.eureka.smartrecruit.domain.User;
import com.eureka.smartrecruit.dto.FavouriteDto;
import com.eureka.smartrecruit.service.FavouriteService;
import com.eureka.smartrecruit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/{userId}/favourites")
public class FavouriteResource {

    private final FavouriteService favouriteService;
    private final UserService userService;
    private final Mapper mapper;

    @RequestMapping(method=RequestMethod.POST, produces={ MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable("userId") Long userId, @RequestBody FavouriteDto favouriteDto) {
        User user = userService.findById(userId);
        Favourite favourite = mapper.map(favouriteDto, Favourite.class);
        favouriteService.create(favourite, user);
    }

    @RequestMapping(method=RequestMethod.PUT, produces={ MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody FavouriteDto favouriteDto) {
        Favourite favourite = favouriteService.findById(favouriteDto.getId());
        favouriteService.update(favourite);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces={ MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        favouriteService.delete(id);
    }

    @RequestMapping(method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public List<FavouriteDto> findAll(@PathVariable("userId") Long userId) {
        User user = userService.findById(userId);
        return user.getFavourites().stream().map(favourite -> mapper.map(favourite, FavouriteDto.class)).collect(Collectors.toList());
    }
}
