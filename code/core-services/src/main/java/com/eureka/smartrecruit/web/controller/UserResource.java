package com.eureka.smartrecruit.web.controller;

import com.eureka.smartrecruit.domain.User;
import com.eureka.smartrecruit.domain.enumeration.UserType;
import com.eureka.smartrecruit.dto.UserDto;
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
@RequestMapping(value="/users")
public class UserResource {

    private final UserService userService;
    private final Mapper mapper;

    @RequestMapping(value="/freelancers", method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public List<UserDto> getFreelancers() {
        return userService.findByType(UserType.FREELANCER).stream().map(user -> mapper.map(user, UserDto.class)).collect(Collectors.toList());
    }








    @RequestMapping(method=RequestMethod.POST, produces={ MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        userService.create(user);
    }

    @RequestMapping(method=RequestMethod.PUT, produces={ MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody UserDto userDto) {
        User user = userService.findById(userDto.getId());
        //user.setName(userDto.getName());
        //user.setDescription(userDto.getDescription());
        //user.setActive(userDto.isActive());
        ///user.setSubCategories(userDto.getSubCategories().stream().map(subUser -> mapper.map(subUser, User.class)).collect(Collectors.toList()));
        userService.update(user);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces={ MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }

    @RequestMapping(method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public List<UserDto> findAll() {
        return userService.findAll().stream().map(user -> mapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @RequestMapping(value="/clients", method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public List<UserDto> getClients() {
        return userService.findByType(UserType.CLIENT).stream().map(user -> mapper.map(user, UserDto.class)).collect(Collectors.toList());
    }



    @RequestMapping(value="/{id}", method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public UserDto findById(@PathVariable("id") Long id) {
        return mapper.map(userService.findById(id), UserDto.class);
    }
}
