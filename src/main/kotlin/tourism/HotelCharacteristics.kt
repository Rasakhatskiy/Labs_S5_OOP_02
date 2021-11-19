package tourism

class HotelCharacteristics (
    var Stars:          UInt,
    var Meals:          MealsType,
    var NumberOfPeople: UInt,
    var HasTV:          Boolean,
    var HasAC:          Boolean){

    constructor() : this(
        0u,
        MealsType.Other,
        0u,
        false,
        false
    )

    override fun toString(): String {
        return "Hotel(Stars=${Stars}, Meals=${Meals}, NumberOfPeople=${NumberOfPeople}, HasTV=${HasTV}, HasAC=${HasAC})"
    }
}