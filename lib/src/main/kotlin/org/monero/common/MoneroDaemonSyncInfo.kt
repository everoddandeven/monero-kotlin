package org.monero.common

class MoneroDaemonSyncInfo {
    var height: ULong = 0u
    var peers: ArrayList<MoneroPeer> = ArrayList()
    var spans: ArrayList<MoneroConnectionSpan> = ArrayList()
    var targetHeight: ULong = 0u
    var nextNeededPruningSeed: Int = 0
    var overview: String = ""
    var credits: ULong = 0u
    var topBlockHash: String = ""
}