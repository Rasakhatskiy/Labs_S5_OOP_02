package parser

import javax.xml.validation.SchemaFactory
import javax.xml.transform.stream.StreamSource
import java.io.File

class Validator {
    fun validate(xmlPath: String, xsdPath: String) {
        val factory: SchemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema")

        val schemaLocation = File(xsdPath)
        val schema = factory.newSchema(schemaLocation)
        val validator = schema.newValidator()
        val source = StreamSource(xmlPath)
        validator.validate(source)
    }
}