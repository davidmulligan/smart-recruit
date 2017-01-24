package com.eureka.smartrecruit.web.controller;

import com.eureka.smartrecruit.domain.User;
import com.eureka.smartrecruit.dto.UserDto;
import com.eureka.smartrecruit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/auth/register")
public class RegisterResource {

    private final UserService userService;
    private final Mapper mapper;

    @RequestMapping(method=RequestMethod.POST, produces={ MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        userService.create(user);
    }
}
