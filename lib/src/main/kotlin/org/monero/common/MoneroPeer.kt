package org.monero.common

class MoneroPeer {
    var id: String = ""
    var address: String = ""
    var host: String = ""
    var port: Int = 0
    var isOnline: Boolean = false
    var lastSeenTimestamp: Long = 0
    var pruningSeed: Int = 0
    var rpcPort: Int = 0
    var rpcCreditsPerHash: ULong = 0u
    var hash: String = ""
    var avgDownload: Long = 0
    var avgUpload: Long = 0
    var currentDownload: Long = 0
    var currentUpload: Long = 0
    var height: ULong = 0u
    var isIncoming: Boolean = false
    var liveTime: Long = 0
    var isLocalIp: Boolean = false
    var isLocalHost: Boolean = false
    var numReceives: Int = 0
    var numSends: Int = 0
    var receivedIdleTime: Long = 0
    var sendIdleTime: Long = 0
    var state: String = ""
    var numSupportFlags: Int = 0
    var type: MoneroConnectionType = MoneroConnectionType.INVALID
}