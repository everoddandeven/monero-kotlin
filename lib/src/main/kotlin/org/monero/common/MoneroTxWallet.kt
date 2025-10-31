package org.monero.common

class MoneroTxWallet : MoneroTx() {
    var txSet: MoneroTxSet? = null
    var isIncoming: Boolean = false
    var isOutgoing: Boolean = false
    var incomingTransfers: List<MoneroIncomingTransfer> = emptyList()
    var outgoingTransfer: MoneroOutgoingTransfer? = null
    var note: String = ""
    var isLocked: Boolean = false
    var inputSum: ULong = 0u
    var outputSum: ULong = 0u
    var changeAddress: String = ""
    var changeAmount: ULong = 0u
    var numDummyOutputs: Int = 0
    var extraHex: String = ""
}