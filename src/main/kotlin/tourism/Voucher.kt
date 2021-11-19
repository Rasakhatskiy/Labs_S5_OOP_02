package tourism

data class Voucher(
    var ID:           UInt,
    var Type:         VoucherType,
    var Country:      String,
    var NumberOfDays: UInt,
    var Transport:    TransportType,
    var Hotel:        HotelCharacteristics,
    var Cost:         Double
) {
    constructor() : this(
        0u,
        VoucherType.Other,
        "",
        0u,
        TransportType.Other,
        HotelCharacteristics(),
        0.0
    )
}