package org.monero.common

class MoneroAccount {
    var index: UInt = 0u
    var primaryAddress: String = ""
    var balance: ULong = 0u
    var unlockedBalance: ULong = 0u
    var tag: String = ""
    var subaddresses: List<MoneroSubaddress> = ArrayList()
}