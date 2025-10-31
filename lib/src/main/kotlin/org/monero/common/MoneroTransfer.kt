package org.monero.common

abstract class MoneroTransfer {
    var tx: MoneroTxWallet? = null
    var amount: ULong = 0u
    var accountIndex: UInt? = null
}