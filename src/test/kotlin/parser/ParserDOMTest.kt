package parser

import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import tourism.TransportType
import tourism.VoucherType
import tourism.HotelCharacteristics
import tourism.MealsType

internal class ParserDOMTest {
    @Test
    fun testParserDom() {
        val parserDOM = ParserDOM()
        val vouchers = parserDOM.parse("Tourist.xml")
        assert(vouchers[3].ID == 4u)
        assert(vouchers[3].Type == VoucherType.Pilgrimage)
        assert(vouchers[3].Country == "Turkey")
        assert(vouchers[3].NumberOfDays == 14u)
        assert(vouchers[3].Transport == TransportType.Plane)
        assert(vouchers[3].Cost == 600.0)

        assert(vouchers[3].Hotel.Stars == 5u)
        assert(vouchers[3].Hotel.Meals == MealsType.AI)
        assert(vouchers[3].Hotel.NumberOfPeople == 5u)
        assert(vouchers[3].Hotel.HasTV)
        assert(vouchers[3].Hotel.HasAC)

    }
}