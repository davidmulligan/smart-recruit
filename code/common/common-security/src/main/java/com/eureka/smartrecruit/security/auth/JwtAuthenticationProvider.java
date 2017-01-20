package com.eureka.smartrecruit.security.auth;

import com.eureka.smartrecruit.security.config.JwtSettings;
import com.eureka.smartrecruit.security.model.token.UnverifiedJwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final JwtSettings jwtSettings;
    private final UserDetailsService userService;

    @Override
    public Authentication authenticate(@NonNull Authentication authentication) throws AuthenticationException {
        UnverifiedJwtToken rawAccessToken = (UnverifiedJwtToken) authentication.getCredentials();
        Jws<Claims> jwsClaims = rawAccessToken.parseClaims(jwtSettings.getTokenSigningKey());
        String subject = jwsClaims.getBody().getSubject();
        UserDetails user = userService.loadUserByUsername(subject);
        return new JwtAuthenticationToken(user, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }
}