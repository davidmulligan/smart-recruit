package com.eureka.smartrecruit.security.factory;

import com.eureka.smartrecruit.security.config.JwtSettings;
import com.eureka.smartrecruit.security.model.Scopes;
import com.eureka.smartrecruit.security.model.token.AccessJwtToken;
import com.eureka.smartrecruit.security.model.token.JwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.jooq.lambda.Seq;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtTokenFactory {

    private final JwtSettings settings;

    public AccessJwtToken createAccessJwtToken(UserDetails userDetails) {
        if (StringUtils.isEmpty(userDetails.getUsername())) {
            throw new IllegalArgumentException("Cannot create JWT Token without username");
        }
        if (userDetails.getAuthorities() == null || userDetails.getAuthorities().isEmpty()) {
            throw new IllegalArgumentException("User doesn't have any privileges");
        }

        ZonedDateTime now = ZonedDateTime.now();
        Claims claims = Jwts.claims().setSubject(userDetails.getUsername());
        claims.put("scopes", Seq.seq(userDetails.getAuthorities()).map(s -> s.toString()).toList());
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(settings.getTokenIssuer())
                .setIssuedAt(Date.from(now.toInstant()))
                .setExpiration(Date.from(now.plusMinutes(settings.getTokenExpirationTime()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
                .compact();
        return new AccessJwtToken(token, claims);
    }

    public JwtToken createRefreshToken(UserDetails userDetails) {
        if (StringUtils.isEmpty(userDetails.getUsername())) {
            throw new IllegalArgumentException("Cannot create JWT Token without username");
        }

        ZonedDateTime now = ZonedDateTime.now();
        Claims claims = Jwts.claims().setSubject(userDetails.getUsername());
        claims.put("scopes", Arrays.asList(Scopes.REFRESH_TOKEN.authority()));
        String token = Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setClaims(claims)
                .setIssuer(settings.getTokenIssuer())
                .setIssuedAt(Date.from(now.toInstant()))
                .setExpiration(Date.from(now.plusMinutes(settings.getRefreshTokenExpTime()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
                .compact();
        return new AccessJwtToken(token, claims);
    }
}