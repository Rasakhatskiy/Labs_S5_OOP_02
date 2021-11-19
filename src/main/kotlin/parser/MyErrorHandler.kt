package parser

import org.xml.sax.ErrorHandler
import org.xml.sax.SAXParseException

class MyErrorHandler : ErrorHandler {
    override fun warning(exception: SAXParseException?) {
       exception?.let {
           throw exception
       }
    }

    override fun error(exception: SAXParseException?) {
        exception?.let {
            throw exception
        }
    }

    override fun fatalError(exception: SAXParseException?) {
        exception?.let {
            throw exception
        }
    }

}