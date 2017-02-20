package com.eureka.smartrecruit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private String companyName;
    private String profile;
    private String type;
    private Double rating;
    private boolean enabled;
    private List<RoleDto> roles;
    private List<SkillDto> skills;
}
