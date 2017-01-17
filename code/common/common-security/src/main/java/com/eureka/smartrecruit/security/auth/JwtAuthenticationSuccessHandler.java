package com.eureka.smartrecruit.security.auth;

import com.eureka.smartrecruit.security.model.UserContext;
import com.eureka.smartrecruit.security.factory.JwtTokenFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper mapper;
    private final JwtTokenFactory tokenFactory;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        mapper.writeValue(response.getWriter(), generateTokenMap((UserContext) authentication.getPrincipal()));
        clearAuthenticationAttributes(request);
    }

    private Map<String, String> generateTokenMap(UserContext userContext) {
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", tokenFactory.createAccessJwtToken(userContext).getToken());
        tokenMap.put("refreshToken", tokenFactory.createRefreshToken(userContext).getToken());
        return tokenMap;
    }

    private final void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }
}