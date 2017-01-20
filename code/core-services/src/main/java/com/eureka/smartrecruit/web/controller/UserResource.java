package com.eureka.smartrecruit.web.controller;

import com.eureka.smartrecruit.dto.UserDto;
import com.eureka.smartrecruit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.dozer.Mapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/users")
public class UserResource {

    private final UserService userService;
    private final Mapper mapper;

    @RequestMapping(method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public List<UserDto> findAll() {
        return userService.findAll().stream().map(user -> mapper.map(user, UserDto.class)).collect(Collectors.toList());
    }
}
