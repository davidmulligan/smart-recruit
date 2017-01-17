package com.eureka.smartrecruit.security.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.StringUtils;

import java.util.Set;

@Getter
@RequiredArgsConstructor
public class UserContext {

    private final String username;
    private final Set<GrantedAuthority> authorities;

    public static UserContext create(String username, Set<GrantedAuthority> authorities) {
        if (StringUtils.isEmpty(username)) {
            throw new IllegalArgumentException("Username has not been set.");
        }
        return new UserContext(username, authorities);
    }
}