package com.eureka.smartrecruit.web.controller.auth;

import com.eureka.smartrecruit.config.WebSecurityConfig;
import com.eureka.smartrecruit.domain.User;
import com.eureka.smartrecruit.security.auth.extractor.TokenExtractor;
import com.eureka.smartrecruit.security.auth.verifier.TokenVerifier;
import com.eureka.smartrecruit.security.config.JwtSettings;
import com.eureka.smartrecruit.security.exception.InvalidJwtToken;
import com.eureka.smartrecruit.security.factory.JwtTokenFactory;
import com.eureka.smartrecruit.security.model.token.JwtToken;
import com.eureka.smartrecruit.security.model.token.RefreshToken;
import com.eureka.smartrecruit.security.model.token.UnverifiedJwtToken;
import com.eureka.smartrecruit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/auth/token")
public class RefreshTokenResource {

    private JwtTokenFactory tokenFactory;
    private JwtSettings jwtSettings;
    private UserService userService;
    private TokenVerifier tokenVerifier;
    private TokenExtractor tokenExtractor;

    @RequestMapping(method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody JwtToken refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String tokenPayload = tokenExtractor.extract(request.getHeader(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM));
        UnverifiedJwtToken unverifiedJwtToken = new UnverifiedJwtToken(tokenPayload);
        RefreshToken refreshToken = RefreshToken.create(unverifiedJwtToken, jwtSettings.getTokenSigningKey()).orElseThrow(() -> new InvalidJwtToken());
        if (!tokenVerifier.verify(refreshToken.getJti())) {
            throw new InvalidJwtToken();
        }
        User user = userService.loadUserByUsername(refreshToken.getSubject());
        return tokenFactory.createAccessJwtToken(user);
    }
}
