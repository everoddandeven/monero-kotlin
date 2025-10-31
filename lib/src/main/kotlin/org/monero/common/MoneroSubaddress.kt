package org.monero.common

class MoneroSubaddress {
    var accountIndex: UInt = 0u
    var index: UInt = 0u
    var address: String = ""
    var label: String = ""
    var balance: ULong = 0u
    var unlockedBalance: ULong = 0u
    var numUnspentOutputs: ULong = 0u
    var isUsed: Boolean = false
    var numBlocksToUnlock: ULong = 0u
}