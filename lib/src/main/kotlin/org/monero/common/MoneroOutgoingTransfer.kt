package org.monero.common

class MoneroOutgoingTransfer : MoneroTransfer() {
    var subaddressIndices: List<UInt> = emptyList()
    var addresses: List<String> = emptyList()
    var destinations: List<MoneroDestination> = emptyList()
}