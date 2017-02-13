package com.eureka.smartrecruit.security.util;

import org.jooq.lambda.Seq;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class SkipPathRequestMatcher implements RequestMatcher {

    private OrRequestMatcher orRequestMatcher;
    private RequestMatcher processingMatcher;

    public SkipPathRequestMatcher(List<String> pathsToSkip, String processingPath) {
        Assert.notNull(pathsToSkip);
        orRequestMatcher = new OrRequestMatcher(Seq.seq(pathsToSkip).map(path -> new AntPathRequestMatcher(path)).collect(Collectors.toList()));
        processingMatcher = new AntPathRequestMatcher(processingPath);
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        if (orRequestMatcher.matches(request)) {
            return false;
        }
        return processingMatcher.matches(request);
    }
}