package org.monero.common

class MoneroHardForkInfo {
    val earliestHeight: ULong = 0u
    var isEnabled: Boolean = false
    var state: Int = 0
    var threshold: Int = 0
    var version: Int = 0
    var numVotes: Int = 0
    var window: Int = 0
    var voting: Int = 0
    var credits: ULong = 0u
    var topBlockHash: String = ""
}