package parser

import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.xml.sax.SAXException

internal class ValidatorTest {
    @Test
    fun testValid() {
        val validator = Validator()
        assertDoesNotThrow {
            validator.validate("Tourist.xml", "Tourist.xsd")
        }
    }

    @Test
    fun testWrongElementOrder() {
        val validator = Validator()
        assertThrows(SAXException::class.java) {
            validator.validate("invalid_01.xml", "Tourist.xsd")
        }
    }

    @Test
    fun testNoProperty() {
        val validator = Validator()
        assertThrows(SAXException::class.java) {
            validator.validate("invalid_02.xml", "Tourist.xsd")
        }
    }

    @Test
    fun testWrongEnumElement() {
        val validator = Validator()
        assertThrows(SAXException::class.java) {
            validator.validate("invalid_03.xml", "Tourist.xsd")
        }
    }
}