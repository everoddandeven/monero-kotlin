package org.monero.common

class MoneroTxSet {
    var txs: List<MoneroTxWallet> = emptyList()
    var multisigTxHex: String = ""
    var unsignedTxHex: String = ""
    var signedTxHex: String = ""
}