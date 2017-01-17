package com.eureka.smartrecruit.security.web

import org.springframework.http.HttpStatus
import spock.lang.Specification
import spock.lang.Subject

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class RestAuthenticationEntryPointTest extends Specification {

    @Subject
    def subject = new RestAuthenticationEntryPoint()

    def "test commence()"() {

        given:
        def request = Mock(HttpServletRequest)
        def response = Mock(HttpServletResponse)

        when:
        subject.commence(request, response, null)

        then:
        1 * response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
    }
}
