package org.monero.common

class MoneroRpcConnection(var uri: String = "", var username: String = "", var password: String = "") {
    var zmqUri: String = ""
    var proxyUri: String = ""
    var priority: Int = 0
    var timeoutMs: Long = 0
    var isOnline: Boolean = false
    var isAuthenticated: Boolean = false
    var responseTime: Long = 0
}