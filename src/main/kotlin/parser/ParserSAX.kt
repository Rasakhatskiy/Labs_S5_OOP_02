package parser


import tourism.Voucher
import java.io.File
import javax.xml.parsers.SAXParser
import javax.xml.parsers.SAXParserFactory


class ParserSAX {
    fun parse(xmlPath: String): List<Voucher> {
        val parserFactory: SAXParserFactory = SAXParserFactory.newInstance()
        val saxParser: SAXParser = parserFactory.newSAXParser()
        val defaultHandler = ParserSAXHandler()
        val stream = File(xmlPath)
        saxParser.parse(stream, defaultHandler)
        return defaultHandler.vouchers
    }
}