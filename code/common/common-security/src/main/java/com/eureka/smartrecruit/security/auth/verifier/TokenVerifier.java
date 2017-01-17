package com.eureka.smartrecruit.security.auth.verifier;

public interface TokenVerifier {

    boolean verify(String payload);
}