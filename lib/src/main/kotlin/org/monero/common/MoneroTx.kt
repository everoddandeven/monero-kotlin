package org.monero.common

open class MoneroTx {
    var block: MoneroBlock? = null
    var hash: String = ""
    var version: Int = 0
    var isMinerTx: Boolean = false
    var paymentId: String = ""
    var fee: ULong = 0u
    var ringSize: Int = 0
    var relay: Boolean = false
    var isRelayed: Boolean = false
    var isConfirmed: Boolean = false
    var inTxPool: Boolean = false
    var numConfirmations: ULong = 0u
    var unlockTime: ULong = 0u
    var lastRelayedTimestamp: ULong = 0u
    var receivedTimestamp: ULong = 0u
    var isDoubleSpendSeen: Boolean = false
    var key: String = ""
    var fullHex: String = ""
    var prunedHex: String = ""
    var prunableHex: String = ""
    var prunableHash: String = ""
    var size: ULong = 0u
    var weight: ULong = 0u
    var inputs: List<MoneroOutput> = emptyList()
    var outputs: List<MoneroOutput> = emptyList()
    var outputIndices: List<ULong> = emptyList()
    var metadata: String = ""
    var extra: Array<Byte> = emptyArray()
    var rctSignatures: Object? = null
    var rctSigPrunable: Object? = null
    var isKeptByBlock: Boolean = false
    var isFailed: Boolean = false
    var lastFailedHeight: ULong = 0u
    var lastFailedHash: String = ""
    var maxUsedBlockHeight: ULong = 0u
    var maxUsedBlockHash: String = ""
    var signatures: List<String> = emptyList()
}
