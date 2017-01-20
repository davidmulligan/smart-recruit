package com.eureka.smartrecruit.security.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "security.jwt")
public class JwtSettings {

    private String tokenIssuer;
    private String tokenSigningKey;
    private Integer tokenExpirationTime;
    private Integer refreshTokenExpTime;
}