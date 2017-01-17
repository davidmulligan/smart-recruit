package com.eureka.smartrecruit.database

import org.hibernate.boot.model.naming.Identifier
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment
import spock.lang.Specification
import spock.lang.Subject

class CustomNamingStrategyTest extends Specification {

    JdbcEnvironment context = Mock()

    @Subject
    def subject = new CustomNamingStrategy()

    def "test toPhysicalTableName()"() {

        when: "The classToTableName method is executed"
        def result = subject.toPhysicalTableName(Identifier.toIdentifier(className), context)

        then: "The result matches the expected table name"
        result.getText() == expectedResult

        where: "We have a valid class name"
        className             | expectedResult
        "Table"               | "TABLE"
        "TableTable"          | "TABLE_TABLE"
        "com.eureka.TableTable" | "COM.EUREKA._TABLE_TABLE"
    }

    def "test toPhysicalColumnName()"() {

        when: "The propertyToColumnName method is executed"
        def result = subject.toPhysicalColumnName(Identifier.toIdentifier(property), context)

        then: "The result matches the expected property name"
        result.getText() == expectedResult

        where: "We have a valid property name"
        property       | expectedResult
        "Column"       | "column"
        "column"       | "column"
        "COLUMN"       | "c_o_l_u_m_n"
        "columnColumn" | "column_column"
    }
}