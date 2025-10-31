package org.monero.daemon

import org.monero.common.MoneroBlockHeader

open class MoneroDaemonListener {
    var lastBlockHeader: MoneroBlockHeader? = null

    fun onBlockHeader(blockHeader: MoneroBlockHeader) {
        lastBlockHeader = blockHeader
    }

}