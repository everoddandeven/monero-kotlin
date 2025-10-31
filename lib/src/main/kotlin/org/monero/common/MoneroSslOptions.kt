package org.monero.common

class MoneroSslOptions {
    var privateKeyPath: String = ""
    var certificatePath: String = ""
    var certificateAuthorityFile: String = ""
    var allowedFingerprints: List<String> = emptyList()
    var allowAnyCert: Boolean = false
}