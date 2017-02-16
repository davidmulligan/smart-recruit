package com.eureka.smartrecruit.config;

import com.eureka.smartrecruit.security.auth.JwtAuthenticationProvider;
import com.eureka.smartrecruit.security.auth.JwtTokenAuthenticationProcessingFilter;
import com.eureka.smartrecruit.security.auth.LoginProcessingFilter;
import com.eureka.smartrecruit.security.auth.UserAuthenticationProvider;
import com.eureka.smartrecruit.security.auth.extractor.TokenExtractor;
import com.eureka.smartrecruit.security.util.SkipPathRequestMatcher;
import com.eureka.smartrecruit.security.web.RestAuthenticationEntryPoint;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final RestAuthenticationEntryPoint authenticationEntryPoint;
    private final AuthenticationSuccessHandler successHandler;
    private final AuthenticationFailureHandler failureHandler;
    private final UserAuthenticationProvider ajaxAuthenticationProvider;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final TokenExtractor tokenExtractor;
    private final ObjectMapper objectMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    public static final String JWT_TOKEN_HEADER_PARAM = "X-Authorization";

    private static final String FORM_BASED_LOGIN_ENTRY_POINT = "/auth/login";
    private static final String REGISTRATION_ENTRY_POINT = "/auth/register";
    private static final String TOKEN_REFRESH_ENTRY_POINT = "/auth/token";
    private static final String TOKEN_BASED_AUTH_ENTRY_POINT = "/**";
    private static final String FREELANCERS_ENTRY_POINT = "/users/freelancers";
    private static final String JOBS_ENTRY_POINT = "/jobs";

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) {
        authenticationManagerBuilder.authenticationProvider(ajaxAuthenticationProvider);
        authenticationManagerBuilder.authenticationProvider(jwtAuthenticationProvider);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .cors()
                .and()
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(this.authenticationEntryPoint)
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                    .antMatchers(FORM_BASED_LOGIN_ENTRY_POINT).permitAll()
                    .antMatchers(TOKEN_REFRESH_ENTRY_POINT).permitAll()
                    .antMatchers(REGISTRATION_ENTRY_POINT).permitAll()
                    .antMatchers(FREELANCERS_ENTRY_POINT).permitAll()
                    .antMatchers(JOBS_ENTRY_POINT).permitAll()
                    .antMatchers("/skills/principal").permitAll()
                    .antMatchers("/categories/principal").permitAll()
                .and()
                    .authorizeRequests()
                    .antMatchers(TOKEN_BASED_AUTH_ENTRY_POINT).authenticated()
                    .antMatchers("/categories/**").authenticated()
                    .antMatchers("/favourites/**").authenticated()
                    .antMatchers("/feedback/**").authenticated()
                    .antMatchers("/memberships/**").authenticated()
                    .antMatchers("/jobs/**").authenticated()
                    .antMatchers("/skills/**").authenticated()
                    .antMatchers("/users/**").authenticated()
                .and()
                    .addFilterBefore(buildAjaxLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
                    .addFilterBefore(buildJwtTokenAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    private LoginProcessingFilter buildAjaxLoginProcessingFilter() throws Exception {
        LoginProcessingFilter filter = new LoginProcessingFilter(FORM_BASED_LOGIN_ENTRY_POINT, successHandler, failureHandler, objectMapper);
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }

    private JwtTokenAuthenticationProcessingFilter buildJwtTokenAuthenticationProcessingFilter() throws Exception {
        List<String> pathsToSkip = Arrays.asList(TOKEN_REFRESH_ENTRY_POINT, FORM_BASED_LOGIN_ENTRY_POINT, REGISTRATION_ENTRY_POINT, FREELANCERS_ENTRY_POINT, JOBS_ENTRY_POINT, "/skills/principal", "/categories/principal");
        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, TOKEN_BASED_AUTH_ENTRY_POINT);
        JwtTokenAuthenticationProcessingFilter filter = new JwtTokenAuthenticationProcessingFilter(failureHandler, tokenExtractor, matcher);
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }
}