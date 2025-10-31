package org.monero.common

class MoneroTransferQuery : MoneroTransfer() {
    var txQuery: MoneroTxQuery? = null
    var isIncoming: Boolean? = null
    var isOutgoing: Boolean? = null
    var address: String = ""
    var addresses: List<String> = emptyList()
    var subaddressIndex: UInt = 0u
    var subaddressIndices: List<UInt> = emptyList()
    var destinations: List<MoneroDestination> = emptyList()
    var hasDestinations: Boolean = false

    fun copy(): MoneroTransferQuery {
        val copy = MoneroTransferQuery()
        copy.txQuery = this.txQuery
        copy.isIncoming = this.isIncoming
        copy.addresses = this.addresses
        copy.addresses = this.addresses
        copy.subaddressIndices = this.subaddressIndices
        copy.subaddressIndices = this.subaddressIndices
        copy.hasDestinations = this.hasDestinations
        copy.destinations = this.destinations
        return copy
    }

}