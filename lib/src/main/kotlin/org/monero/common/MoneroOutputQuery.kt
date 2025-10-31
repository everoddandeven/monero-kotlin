package org.monero.common

class MoneroOutputQuery : MoneroOutput() {
    var txQuery: MoneroTxQuery? = null
    var subaddressIndices: List<UInt> = emptyList()
    var minAmount: UInt? = null
    var maxAmount: UInt? = null
}