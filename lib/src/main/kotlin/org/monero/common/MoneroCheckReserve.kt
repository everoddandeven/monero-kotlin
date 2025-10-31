package org.monero.common

class MoneroCheckReserve : MoneroCheck() {
    var totalAmount: ULong = 0u
    var unconfirmedSpentAmount: ULong = 0u
}