package com.eureka.smartrecruit.security.factory

import com.eureka.smartrecruit.security.config.JwtSettings
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import spock.lang.Specification
import spock.lang.Subject

class JwtTokenFactoryTest extends Specification {

    def jwtSettings = new JwtSettings("issuer", "VEVTVA==", 15, 30)

    @Subject
    def subject = new JwtTokenFactory(jwtSettings)

    def "test createAccessJwtToken()"() {

        given:
        def userDetails = Mock(UserDetails)
        userDetails.getUsername() >> "username"
        userDetails.getAuthorities() >> [new SimpleGrantedAuthority("ROLE")]

        when:
        def result = subject.createAccessJwtToken(userDetails)

        then:
        result.getToken() != null
        result.getClaims().get("sub", String) == userDetails.getUsername()
        result.getClaims().get("scopes", List) == userDetails.getAuthorities().collect { it.toString() }
        result.getClaims().get("iss", String) == jwtSettings.getTokenIssuer()
        result.getClaims().get("iat", Date) != null
        result.getClaims().get("exp", Date) != null
    }

    def "test createAccessJwtToken() - Ensure an exception is thrown when a username is not provided"() {

        given:
        def userDetails = Mock(UserDetails)
        userDetails.getAuthorities() >> [new SimpleGrantedAuthority("ROLE")]

        when:
        subject.createAccessJwtToken(userDetails)

        then:
        def e = thrown(IllegalArgumentException)
        e.getMessage() == "Cannot create JWT Token without username"
    }

    def "test createAccessJwtToken() - Ensure an exception is thrown when the authorities are not provided"() {

        given:
        def userDetails = Mock(UserDetails)
        userDetails.getUsername() >> "username"

        when:
        subject.createAccessJwtToken(userDetails)

        then:
        def e = thrown(IllegalArgumentException)
        e.getMessage() == "User doesn't have any privileges"
    }

    def "test createAccessJwtToken() - Ensure an exception is thrown when an empty list of authorities are provided"() {

        given:
        def userDetails = Mock(UserDetails)
        userDetails.getUsername() >> "username"
        userDetails.getAuthorities() >> []

        when:
        subject.createAccessJwtToken(userDetails)

        then:
        def e = thrown(IllegalArgumentException)
        e.getMessage() == "User doesn't have any privileges"
    }
}
