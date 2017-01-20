package com.eureka.smartrecruit.security.auth;

import com.eureka.smartrecruit.security.exception.AuthMethodNotSupportedException;
import com.eureka.smartrecruit.security.exception.JwtExpiredTokenException;
import com.eureka.smartrecruit.security.model.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper mapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        if (exception instanceof BadCredentialsException || exception instanceof JwtExpiredTokenException || exception instanceof AuthMethodNotSupportedException) {
            mapper.writeValue(response.getWriter(), new ErrorResponse(exception.getMessage(), HttpStatus.UNAUTHORIZED));
        }
        mapper.writeValue(response.getWriter(), new ErrorResponse("Authentication failed", HttpStatus.UNAUTHORIZED));
    }
}