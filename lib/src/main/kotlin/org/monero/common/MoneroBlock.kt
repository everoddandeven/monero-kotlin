package org.monero.common

class MoneroBlock : MoneroBlockHeader() {
    val hex: String = ""
    val txs: ArrayList<MoneroTx> = ArrayList()
    val minerTx: MoneroTx? = null
    val txHashes: ArrayList<String> = ArrayList()
}
