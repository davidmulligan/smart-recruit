package com.eureka.smartrecruit.security.auth.extractor;

public interface TokenExtractor {

    String extract(String payload);
}