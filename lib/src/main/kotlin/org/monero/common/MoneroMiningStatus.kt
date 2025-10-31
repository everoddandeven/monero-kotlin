package org.monero.common

class MoneroMiningStatus {
    var isActive: Boolean = false
    var isBackground: Boolean = false
    var address: String = ""
    var speed: ULong = 0u
    var numThreads: Int = 0
}