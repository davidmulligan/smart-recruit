package com.eureka.smartrecruit.security.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpMethod;

@Getter
@Setter
@RequiredArgsConstructor
public class Path {

    private final String path;
    private final HttpMethod method;

    public Path(String path) {
        this.path = path;
        this.method = HttpMethod.GET;
    }
}
