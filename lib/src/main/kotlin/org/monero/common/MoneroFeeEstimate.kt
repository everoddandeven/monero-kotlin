package org.monero.common

class MoneroFeeEstimate {
    var fee: ULong = 0u
    var fees: ArrayList<ULong> = ArrayList()
    var quantizationMask: ULong = 0u
}