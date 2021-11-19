package parser

import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler
import tourism.*

class ParserSAXHandler : DefaultHandler() {
    val vouchers = mutableListOf<Voucher>()

    override fun startElement(
        uri: String,
        localName: String,
        qName: String,
        attributes: Attributes
    ) {
        currentElement = true
        currentValue = ""
        if (qName == AttrName.Voucher.toString())
            currentVoucher = Voucher()
        if (qName == AttrName.HotelCharacteristics.toString())
            currentHotel = HotelCharacteristics()

    }

    override fun endElement(
        uri: String,
        localName: String,
        qName: String
    ) {
        currentElement = false



        when (qName) {
            AttrName.ID.toString() -> currentVoucher.ID = currentValue.toUInt()
            AttrName.Type.toString() -> currentVoucher.Type = VoucherType.valueOf(currentValue)
            AttrName.Country.toString()-> currentVoucher.Country = currentValue
            AttrName.NumberOfDays.toString() -> currentVoucher.NumberOfDays = currentValue.toUInt()
            AttrName.Transport.toString() -> currentVoucher.Transport = TransportType.valueOf(currentValue)
            AttrName.Cost.toString() -> currentVoucher.Cost = currentValue.toDouble()

            AttrName.Stars.toString() -> currentHotel.Stars = currentValue.toUInt()
            AttrName.Meals.toString()-> currentHotel.Meals = MealsType.valueOf(currentValue)
            AttrName.NumberOfPeople.toString()-> currentHotel.NumberOfPeople = currentValue.toUInt()
            AttrName.HasTV.toString() -> currentHotel.HasTV = currentValue.toBoolean()
            AttrName.HasAC.toString() -> currentHotel.HasAC = currentValue.toBoolean()
        }


        if (qName.equals(AttrName.Voucher.toString(), ignoreCase = true)) {
            currentVoucher.Hotel = currentHotel
            vouchers.add(currentVoucher)
        }
    }

    override fun characters(ch: CharArray, start: Int, length: Int) {
        if (currentElement) {
            currentValue += String(ch, start, length)
        }
    }

    private var currentValue = ""
    private var currentElement = false
    private var currentVoucher = Voucher()
    private var currentHotel = HotelCharacteristics()
}