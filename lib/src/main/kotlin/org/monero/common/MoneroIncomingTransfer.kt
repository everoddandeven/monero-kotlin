package org.monero.common

class MoneroIncomingTransfer : MoneroTransfer() {
    var subaddressIndex: UInt = 0u
    var address: String = ""
    var numSuggestedConfirmations: ULong = 0u
}