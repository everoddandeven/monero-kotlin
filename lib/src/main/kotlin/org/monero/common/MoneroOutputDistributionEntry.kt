package org.monero.common

class MoneroOutputDistributionEntry {
    var amount: ULong = 0u
    var base: Int = 0
    var distribution: ArrayList<Int> = ArrayList()
    var startHeight: ULong = 0u
}