package org.monero.common

open class MoneroDaemonUpdateCheckResult {
    var isUpdateAvailable: Boolean = false
    var version: String = ""
    var hash: String = ""
    var autoUri: String = ""
    var userUri: String = ""
}