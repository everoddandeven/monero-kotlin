package org.monero.daemon

import org.monero.common.MoneroAltChain
import org.monero.common.MoneroBan
import org.monero.common.MoneroBlock
import org.monero.common.MoneroBlockHeader
import org.monero.common.MoneroBlockTemplate
import org.monero.common.MoneroDaemonInfo
import org.monero.common.MoneroDaemonSyncInfo
import org.monero.common.MoneroDaemonUpdateCheckResult
import org.monero.common.MoneroDaemonUpdateDownloadResult
import org.monero.common.MoneroFeeEstimate
import org.monero.common.MoneroHardForkInfo
import org.monero.common.MoneroKeyImageSpentStatus
import org.monero.common.MoneroMinerTxSum
import org.monero.common.MoneroMiningStatus
import org.monero.common.MoneroOutput
import org.monero.common.MoneroPeer
import org.monero.common.MoneroPruneResult
import org.monero.common.MoneroSubmitTxResult
import org.monero.common.MoneroTx
import org.monero.common.MoneroVersion

interface MoneroDaemon {
    fun addListener(listener: MoneroDaemonListener)
    fun removeListener(listener: MoneroDaemonListener)
    fun getListeners(): List<MoneroDaemonListener>
    fun getVersion(): MoneroVersion
    fun isTrusted(): Boolean
    fun getHeight(): ULong
    fun getBlockHash(height: ULong): String
    fun getBlockTemplate(walletAddress: String): MoneroBlockTemplate
    fun getLastBlockHeader(): MoneroBlockHeader
    fun getBlockHeaderByHash(blockHash: String): MoneroBlockHeader
    fun getBlockHeaderByHeight(height: ULong): MoneroBlockHeader
    fun getBlockHeadersByRange(startHeight: ULong, endHeight: ULong): List<MoneroBlockHeader>
    fun getBlockByHash(blockHash: String): MoneroBlock
    fun getBlockByHeight(height: ULong): MoneroBlock
    fun getBlocksByHeight(heights: List<ULong>): MoneroBlock
    fun getBlocksByRange(startHeight: ULong, endHeight: ULong): List<MoneroBlock>
    fun getBlocksByRangeChunked(startHeight: ULong, endHeight: ULong): List<MoneroBlock>
    fun getBlocksByRangeChunked(startHeight: ULong, endHeight: ULong, maxChunkSize: ULong): List<MoneroBlock>
    fun getBlockHashes(blockHashes: List<String>, startHeight: ULong): List<String>
    fun getTx(txHash: String, prune: Boolean = false): MoneroTx
    fun getTxs(txs: List<MoneroTx>, prune: Boolean = false): List<MoneroTx>
    fun getTxHex(txHash: String, prune: Boolean = false): String
    fun getTxHexes(txHashes: List<MoneroTx>, prune: Boolean = false): List<String>
    fun getMinerTxSum(height: ULong, numBlocks: ULong): MoneroMinerTxSum
    fun getFeeEstimate(): MoneroFeeEstimate
    fun submitTxHex(txHex: String, doNotRelay: Boolean = false): MoneroSubmitTxResult
    fun relayTxByHash(txHash: String)
    fun relayTxsByHash(txHashes: List<String>)
    fun getTxPool(): List<MoneroTx>
    fun getTxPoolHashes(): List<String>
    fun flushTxPool(hashes: List<String>)
    fun getKeyImageSpentStatus(keyImage: String): MoneroKeyImageSpentStatus
    fun getKeyImageSpentStatuses(keyImages: List<String>): List<MoneroKeyImageSpentStatus>
    fun getOutputs(outputs: List<MoneroOutput>): List<MoneroOutput>

    fun getInfo(): MoneroDaemonInfo
    fun getSyncInfo(): MoneroDaemonSyncInfo
    fun getHardForkInfo(): MoneroHardForkInfo
    fun getAltChains(): List<MoneroAltChain>
    fun getAltBlockHashes(): List<String>
    fun getDownloadLimit(): Int
    fun setDownloadLimit(limit: Int): Int
    fun resetDownloadLimit(): Int
    fun getUploadLimit(): Int
    fun setUploadLimit(limit: Int): Int
    fun resetUploadLimit(): Int
    fun getPeers(): List<MoneroPeer>
    fun getKnownPeers(): List<MoneroPeer>
    fun setOutgoingPeerLimit(limit: Int)
    fun setIncomingPeerLimit(limit: Int)
    fun getPeerBans(): List<MoneroBan>
    fun setPeerBans(bans: List<MoneroBan>)
    fun setPeerBan(ban: MoneroBan)
    fun startMining(address: String, numThreads: Int, isBackground: Boolean, ignoreBattery: Boolean)
    fun stopMining()
    fun getMiningStatus(): MoneroMiningStatus
    fun submitBlock(blockBlob: String)
    fun submitBlocks(blockBlobs: List<MoneroBlock>)
    fun pruneBlockchain(check: Boolean): MoneroPruneResult
    fun checkForUpdate(): MoneroDaemonUpdateCheckResult
    fun downloadUpdate(path: String = ""): MoneroDaemonUpdateDownloadResult
    fun dispose()
    fun waitForNextBlockHeader(): MoneroBlockHeader


}