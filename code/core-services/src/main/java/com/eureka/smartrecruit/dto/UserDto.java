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
    private String email;
    private String companyName;
    private String profile;
    private String type;
    private boolean enabled;
    private List<SkillDto> skills;
    private List<RoleDto> roles;
}
