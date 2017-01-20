package com.eureka.smartrecruit.security.auth.extractor;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class JwtTokenExtractor implements TokenExtractor {

    private static String HEADER_PREFIX = "Bearer ";

    @Override
    public String extract(String header) {
        if (StringUtils.isEmpty(header)) {
            throw new AuthenticationServiceException("Authorization header cannot be empty");
        } else if (!header.startsWith(HEADER_PREFIX)) {
            throw new AuthenticationServiceException("Authorization header is invalid");
        }
        return header.substring(HEADER_PREFIX.length(), header.length());
    }
}