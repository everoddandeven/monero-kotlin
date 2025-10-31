package org.monero.common

class MoneroCheckTx : MoneroCheck() {
    var inTxPool: Boolean = false
    var numConfirmations: ULong = 0u
    var receivedAmount: ULong = 0u
}