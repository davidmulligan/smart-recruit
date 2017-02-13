package com.eureka.smartrecruit.web.controller;

import com.eureka.smartrecruit.domain.User;
import com.eureka.smartrecruit.domain.enumeration.UserType;
import com.eureka.smartrecruit.dto.UserDto;
import com.eureka.smartrecruit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.dozer.Mapper;
import org.jooq.lambda.Seq;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/users")
public class UserResource {

    private final UserService userService;
    private final Mapper mapper;

    @RequestMapping(method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody UserDto userDto) {
        userService.create(mapper.map(userDto, User.class));
    }

    @RequestMapping(method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody UserDto userDto) {
        User user = userService.findById(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        userService.update(user);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public UserDto findById(@PathVariable("id") Long id) {
        return mapper.map(userService.findById(id), UserDto.class);
    }

    @RequestMapping(method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public List<UserDto> findAll() {
        return Seq.seq(userService.findAll()).map(user -> mapper.map(user, UserDto.class)).toList();
    }

    @RequestMapping(value="/freelancers", method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public List<UserDto> getFreelancers() {
        return Seq.seq(userService.findByType(UserType.FREELANCER)).map(user -> mapper.map(user, UserDto.class)).toList();
    }

    @RequestMapping(value="/clients", method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public List<UserDto> getClients() {
        return Seq.seq(userService.findByType(UserType.CLIENT)).map(user -> mapper.map(user, UserDto.class)).toList();
    }
}
