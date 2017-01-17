package com.eureka.smartrecruit.security.exception;

import com.eureka.smartrecruit.security.model.token.JwtToken;
import org.springframework.security.core.AuthenticationException;

public class JwtExpiredTokenException extends AuthenticationException {

    private JwtToken token;

    public JwtExpiredTokenException(String message) {
        super(message);
    }

    public JwtExpiredTokenException(JwtToken token, String message, Throwable throwable) {
        super(message, throwable);
        this.token = token;
    }

    public String token() {
        return token.getToken();
    }
}