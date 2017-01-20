package com.eureka.smartrecruit.security.auth.extractor

import org.springframework.security.authentication.AuthenticationServiceException
import spock.lang.Specification
import spock.lang.Subject

class JwtTokenExtractorTest extends Specification {

    @Subject
    def subject = new JwtTokenExtractor()

    def "test extract()"() {

        given:
        def token = "XXXXXXXXXX"
        def header = "Bearer ${token}"

        when:
        def result = subject.extract(header)

        then:
        result == token
    }

    def "test extract() - Ensure an exception is thrown when a header is not provided"() {

        when:
        subject.extract(null)

        then:
        def e = thrown(AuthenticationServiceException)
        e.getMessage() == "Authorization header cannot be empty"
    }

    def "test extract() - Ensure an exception is thrown when an empty header is provided"() {

        when:
        subject.extract("")

        then:
        def e = thrown(AuthenticationServiceException)
        e.getMessage() == "Authorization header cannot be empty"
    }

    def "test extract() - Ensure an exception is thrown when an invalid header is provided"() {

        given:
        def token = "XXXXXXXXXX"
        def header = "Invalid ${token}"

        when:
        subject.extract(header)

        then:
        def e = thrown(AuthenticationServiceException)
        e.getMessage() == "Authorization header is invalid"
    }
}
