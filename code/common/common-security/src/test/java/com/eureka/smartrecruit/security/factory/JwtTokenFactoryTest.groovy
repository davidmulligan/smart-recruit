package com.eureka.smartrecruit.security.factory

import com.eureka.smartrecruit.security.config.JwtSettings
import com.eureka.smartrecruit.security.model.UserContext
import org.springframework.security.core.authority.SimpleGrantedAuthority
import spock.lang.Specification
import spock.lang.Subject

class JwtTokenFactoryTest extends Specification {

    def jwtSettings = new JwtSettings("issuer", "VEVTVA==", 15, 30)

    @Subject
    def subject = new JwtTokenFactory(jwtSettings)

    def "test createAccessJwtToken()"() {

        given:
        def userContext = new UserContext("username", [new SimpleGrantedAuthority("ROLE")] as Set)

        when:
        def result = subject.createAccessJwtToken(userContext)

        then:
        result.getToken() != null
        result.getClaims().get("sub", String) == userContext.getUsername()
        result.getClaims().get("scopes", List) == userContext.getAuthorities().collect { it.toString() }
        result.getClaims().get("iss", String) == jwtSettings.getTokenIssuer()
        result.getClaims().get("iat", Date) != null
        result.getClaims().get("exp", Date) != null
    }

    def "test createAccessJwtToken() - Ensure an exception is thrown when a username is not provided"() {

        given:
        def userContext = new UserContext(null, [new SimpleGrantedAuthority("ROLE")] as Set)

        when:
        subject.createAccessJwtToken(userContext)

        then:
        def e = thrown(IllegalArgumentException)
        e.getMessage() == "Cannot create JWT Token without username"
    }

    def "test createAccessJwtToken() - Ensure an exception is thrown when the authorities are not provided"() {

        given:
        def userContext = new UserContext("username", null)

        when:
        subject.createAccessJwtToken(userContext)

        then:
        def e = thrown(IllegalArgumentException)
        e.getMessage() == "User doesn't have any privileges"
    }

    def "test createAccessJwtToken() - Ensure an exception is thrown when an empty list of authorities are provided"() {

        given:
        def userContext = new UserContext("username",  [] as Set)

        when:
        subject.createAccessJwtToken(userContext)

        then:
        def e = thrown(IllegalArgumentException)
        e.getMessage() == "User doesn't have any privileges"
    }
}
