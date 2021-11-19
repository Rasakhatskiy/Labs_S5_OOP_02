package parser

import tourism.HotelCharacteristics
import tourism.MealsType
import tourism.TransportType
import tourism.Voucher
import tourism.VoucherType
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.xml.sax.SAXException
import java.io.File
import java.io.IOException
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException


class ParserDOM {
    fun parse(xmlPath: String): List<Voucher> {
        val vouchers = mutableListOf<Voucher>()
        try {
            val stream = File(xmlPath)
            val builderFactory = DocumentBuilderFactory.newInstance()
            val docBuilder = builderFactory.newDocumentBuilder()
            val doc = docBuilder.parse(stream)
            val nList = doc.getElementsByTagName(AttrName.Voucher.toString())

            for (i in 0 until nList.length) {
                if (nList.item(i).nodeType == Node.ELEMENT_NODE) {
                    val element = nList.item(i) as Element
                    val hotelElement = element.getElementsByTagName(AttrName.HotelCharacteristics.toString()).item(0) as Element
                    val hotel = HotelCharacteristics(
                        hotelElement.getElementsByTagName(AttrName.Stars.toString()).item(0).textContent.toUInt(),
                        MealsType.valueOf(hotelElement.getElementsByTagName(AttrName.Meals.toString()).item(0).textContent),
                        hotelElement.getElementsByTagName(AttrName.NumberOfPeople.toString()).item(0).textContent.toUInt(),
                        hotelElement.getElementsByTagName(AttrName.HasTV.toString()).item(0).textContent.toBoolean(),
                        hotelElement.getElementsByTagName(AttrName.HasAC.toString()).item(0).textContent.toBoolean()
                    )
                    val voucher = Voucher(
                        element.getElementsByTagName(AttrName.ID.toString()).item(0).textContent.toUInt(),
                        VoucherType.valueOf(element.getElementsByTagName(AttrName.Type.toString()).item(0).textContent),
                        element.getElementsByTagName(AttrName.Country.toString()).item(0).textContent,
                        element.getElementsByTagName(AttrName.NumberOfDays.toString()).item(0).textContent.toUInt(),
                        TransportType.valueOf(element.getElementsByTagName(AttrName.Transport.toString()).item(0).textContent),
                        hotel,
                        element.getElementsByTagName(AttrName.Cost.toString()).item(0).textContent.toDouble(),
                    )
                    vouchers.add(voucher)
                }
            }
        } catch (e: IOException) {
            throw e
        } catch (e: ParserConfigurationException) {
            throw e
        } catch (e: SAXException) {
            throw e
        }
        return vouchers
    }
}