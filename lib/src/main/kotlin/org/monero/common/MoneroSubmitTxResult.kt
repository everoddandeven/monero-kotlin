package org.monero.common

class MoneroSubmitTxResult {
    var isGood: Boolean = false
    var isRelayed: Boolean = false
    var isDoubleSpend: Boolean = false
    var isFeeTooLow: Boolean = false
    var isMixinTooLow: Boolean = false
    var hasInvalidInput: Boolean = false
    var hasInvalidOutput: Boolean = false
    var hasTooFewOutputs: Boolean = false
    var isOverspend: Boolean = false
    var isTooBig: Boolean = false
    var sanityCheckFailed: Boolean = false
    var reason: String = ""
    var credits: ULong = 0u
    var topBlockHash: String = ""
    var isTxExtraTooBig: Boolean = false
    var isNonzeroUnlockTime: Boolean = false
}