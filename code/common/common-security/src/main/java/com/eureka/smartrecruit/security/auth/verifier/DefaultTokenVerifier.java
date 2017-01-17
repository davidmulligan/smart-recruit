package com.eureka.smartrecruit.security.auth.verifier;

import org.springframework.stereotype.Component;

@Component
public class DefaultTokenVerifier implements TokenVerifier {

    @Override
    public boolean verify(String payload) {
        return true;
    }
}