package org.monero.common

class MoneroMessageSignatureResult {
    var isGood: Boolean = false
    var isOld: Boolean = false
    var signatureType: MoneroMessageSignatureType = MoneroMessageSignatureType.INVALID
}