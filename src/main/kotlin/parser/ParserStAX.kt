package parser

import tourism.*
import java.io.FileInputStream
import javax.xml.stream.XMLEventReader
import javax.xml.stream.XMLInputFactory


class ParserStAX {
    fun parse(xmlPath: String): List<Voucher> {
        val xmlInputFactory = XMLInputFactory.newInstance()
        val reader: XMLEventReader = xmlInputFactory.createXMLEventReader(FileInputStream(xmlPath))

        var voucher = Voucher()
        var hotel = HotelCharacteristics()
        var vouchers = mutableListOf<Voucher>()


        //var eventType = reader.getEventType();
        while (reader.hasNext()) {
            val event = reader.nextEvent()
            if (event.isStartElement) {
                val startElement = event.asStartElement()
                val name = startElement.name.localPart

                if (name == AttrName.Voucher.toString()) {
                    voucher = Voucher()
                    continue
                }

                if (name == AttrName.HotelCharacteristics.toString()) {
                    hotel = HotelCharacteristics()
                    continue
                }

                val data = reader.nextEvent().asCharacters().data
                when (name) {
                    AttrName.ID.toString() -> voucher.ID = data.toUInt()
                    AttrName.Type.toString() -> voucher.Type = VoucherType.valueOf(data)
                    AttrName.Country.toString() -> voucher.Country = data
                    AttrName.NumberOfDays.toString() -> voucher.NumberOfDays = data.toUInt()
                    AttrName.Transport.toString() -> voucher.Transport = TransportType.valueOf(data)
                    AttrName.Cost.toString() -> voucher.Cost = data.toDouble()

                    AttrName.Stars.toString() -> hotel.Stars = data.toUInt()
                    AttrName.Meals.toString() -> hotel.Meals = MealsType.valueOf(data)
                    AttrName.NumberOfPeople.toString() -> hotel.NumberOfPeople = data.toUInt()
                    AttrName.HasTV.toString() -> hotel.HasTV = data.toBoolean()
                    AttrName.HasAC.toString() -> hotel.HasAC = data.toBoolean()
                }
            }

            if (event.isEndElement) {
                val endElement = event.asEndElement()
                val name = endElement.name.localPart

                if (name == AttrName.Voucher.toString()) {
                    vouchers.add(voucher)
                }
                if (name == AttrName.HotelCharacteristics.toString()) {
                    voucher.Hotel = hotel
                }
            }

        }
        return vouchers
    }
}