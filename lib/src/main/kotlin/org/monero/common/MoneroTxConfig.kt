package org.monero.common

class MoneroTxConfig {
    var destinations: List<MoneroDestination> = emptyList()
    var subtractFeeFrom: List<Int> = emptyList()
    var paymentId: String = ""
    var priority: MoneroTxPriority = MoneroTxPriority.DEFAULT
    var fee: ULong = 0u
    var accountIndex: UInt? = null
    var subaddressIndices: List<Int> = emptyList()
    var canSplit: Boolean = false
    var relay: Boolean = false
    var note: String = ""
    var recipientName: String = ""
    var belowAmount: ULong = 0u
    var sweepEachSubaddress: Boolean = false
    var keyImage: String = ""

    fun copy(): MoneroTxConfig {
        val copy = MoneroTxConfig()
        copy.destinations = destinations
        copy.subtractFeeFrom = subtractFeeFrom
        copy.paymentId = paymentId
        copy.priority = priority
        copy.fee = fee
        copy.accountIndex = accountIndex
        copy.subaddressIndices = subaddressIndices
        copy.canSplit = canSplit
        copy.relay = relay
        copy.note = note
        copy.recipientName = recipientName
        copy.belowAmount = belowAmount
        copy.sweepEachSubaddress = sweepEachSubaddress
        copy.keyImage = keyImage
        return copy
    }
}