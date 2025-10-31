package org.monero.common

open class MoneroOutputWallet : MoneroOutput() {
    var accountIndex: UInt = 0u
    var subaddressIndex: UInt = 0u
    var isSpent: Boolean = false
    var isFrozen: Boolean = false
}