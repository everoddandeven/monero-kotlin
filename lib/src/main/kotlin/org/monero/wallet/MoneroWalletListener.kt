package org.monero.wallet

import org.monero.common.MoneroOutputWallet

interface MoneroWalletListener {
    fun onSyncProgress(height: ULong, startHeight: ULong, endHeight: ULong, percentDone: Double, message: String)
    fun onNewBlock(height: ULong)
    fun onBalancesChanged(newBalance: ULong, newUnlockedBalance: ULong)
    fun onOutputReceived(output: MoneroOutputWallet)
    fun onOutputSpent(output: MoneroOutputWallet)
}