package com.eureka.smartrecruit.web.controller;

import com.eureka.smartrecruit.config.WebSecurityConfig;
import com.eureka.smartrecruit.security.auth.extractor.TokenExtractor;
import com.eureka.smartrecruit.security.auth.verifier.TokenVerifier;
import com.eureka.smartrecruit.security.config.JwtSettings;
import com.eureka.smartrecruit.security.exception.InvalidJwtToken;
import com.eureka.smartrecruit.security.model.UserContext;
import com.eureka.smartrecruit.security.model.token.JwtToken;
import com.eureka.smartrecruit.security.factory.JwtTokenFactory;
import com.eureka.smartrecruit.security.model.token.UnverifiedJwtToken;
import com.eureka.smartrecruit.security.model.token.RefreshToken;
import com.eureka.smartrecruit.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@RestController
public class RefreshTokenEndpoint {
    @Autowired private JwtTokenFactory tokenFactory;
    @Autowired private JwtSettings jwtSettings;
    @Autowired private UserDetailsService userService;
    @Autowired private TokenVerifier tokenVerifier;
    @Autowired @Qualifier("jwtHeaderTokenExtractor") private TokenExtractor tokenExtractor;

    @RequestMapping(value="/api/auth/token", method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody
    JwtToken refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String tokenPayload = tokenExtractor.extract(request.getHeader(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM));

        UnverifiedJwtToken rawToken = new UnverifiedJwtToken(tokenPayload);
        RefreshToken refreshToken = RefreshToken.create(rawToken, jwtSettings.getTokenSigningKey()).orElseThrow(() -> new InvalidJwtToken());

        String jti = refreshToken.getJti();
        if (!tokenVerifier.verify(jti)) {
            throw new InvalidJwtToken();
        }

        String subject = refreshToken.getSubject();
        UserDetails user = userService.loadUserByUsername(subject);

       // if (user.getRoles() == null) throw new InsufficientAuthenticationException("User has no roles assigned");
        //List<GrantedAuthority> authorities = user.getRoles().stream()
          //      .map(authority -> new SimpleGrantedAuthority(authority.getRole().authority()))
            //    .collect(Collectors.toList());

        UserContext userContext = UserContext.create(user.getUsername(), (Set<GrantedAuthority>) user.getAuthorities());

        return tokenFactory.createAccessJwtToken(userContext);
    }
}
