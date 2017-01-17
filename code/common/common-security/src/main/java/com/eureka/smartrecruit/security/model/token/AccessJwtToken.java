package com.eureka.smartrecruit.security.model.token;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.jsonwebtoken.Claims;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public final class AccessJwtToken implements JwtToken {

    private final String token;

    @JsonIgnore
    private final Claims claims;
}