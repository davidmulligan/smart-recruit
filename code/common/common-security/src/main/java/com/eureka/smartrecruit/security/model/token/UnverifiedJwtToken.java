package com.eureka.smartrecruit.security.model.token;

import com.eureka.smartrecruit.security.exception.JwtExpiredTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;

@Getter
@RequiredArgsConstructor
public class UnverifiedJwtToken implements JwtToken {

    private final String token;

    public Jws<Claims> parseClaims(String signingKey) {
        try {
            return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token);
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException e) {
            throw new BadCredentialsException("Invalid JWT token", e);
        } catch (ExpiredJwtException e) {
            throw new JwtExpiredTokenException(this, "JWT token has expired", e);
        }
    }
}