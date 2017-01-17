package com.eureka.smartrecruit.security.auth;

import com.eureka.smartrecruit.security.exception.AuthMethodNotSupportedException;
import com.eureka.smartrecruit.security.model.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private final AuthenticationSuccessHandler successHandler;
    private final AuthenticationFailureHandler failureHandler;
    private final ObjectMapper objectMapper;

    private static final String XML_HTTP_REQUEST = "XMLHttpRequest";
    private static final String X_REQUESTED_WITH = "X-Requested-With";

    public LoginProcessingFilter(String defaultProcessUrl, AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler, ObjectMapper mapper) {
        super(defaultProcessUrl);
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
        this.objectMapper = mapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (!HttpMethod.POST.name().equals(request.getMethod()) || !XML_HTTP_REQUEST.equals(request.getHeader(X_REQUESTED_WITH))) {
            throw new AuthMethodNotSupportedException("Authentication method not supported");
        }

        LoginRequest loginRequest = objectMapper.readValue(request.getReader(), LoginRequest.class);
        if (StringUtils.isEmpty(loginRequest.getUsername()) || StringUtils.isEmpty(loginRequest.getPassword())) {
            throw new AuthenticationServiceException("Username or Password not provided");
        }
        return this.getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        successHandler.onAuthenticationSuccess(request, response, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        failureHandler.onAuthenticationFailure(request, response, failed);
    }
}