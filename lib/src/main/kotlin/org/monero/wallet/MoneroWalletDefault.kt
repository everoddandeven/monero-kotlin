package org.monero.wallet

import org.monero.common.MoneroAccount
import org.monero.common.MoneroError
import org.monero.common.MoneroIncomingTransfer
import org.monero.common.MoneroIntegratedAddress
import org.monero.common.MoneroMessageSignatureType
import org.monero.common.MoneroOutgoingTransfer
import org.monero.common.MoneroOutput
import org.monero.common.MoneroOutputQuery
import org.monero.common.MoneroOutputWallet
import org.monero.common.MoneroRpcConnection
import org.monero.common.MoneroSubaddress
import org.monero.common.MoneroSyncResult
import org.monero.common.MoneroTransfer
import org.monero.common.MoneroTransferQuery
import org.monero.common.MoneroTxConfig
import org.monero.common.MoneroTxQuery
import org.monero.common.MoneroTxSet
import org.monero.common.MoneroTxWallet

abstract class MoneroWalletDefault : MoneroWallet {
    protected var listeners: MutableSet<MoneroWalletListener> = mutableSetOf()
    protected var isClosed: Boolean = false

    protected fun normalizeTransferQuery(query: MoneroTransferQuery?): MoneroTransferQuery {
        var q = query

        if (q == null) {
            return MoneroTransferQuery()
        }
        else {
            if (q.txQuery == null) {
                q = q.copy()
            }
            else {
                val tq = q.txQuery!!
                val txQuery = tq.copy()
                if (tq.transferQuery == q) {
                    q = txQuery.transferQuery
                }
                else {
                    if (q.txQuery == null || q.txQuery!!.transferQuery == null) {
                        throw MoneroError("Transfer query's tx query must be circular reference or null")
                    }

                    q = q.copy()
                    q.txQuery = txQuery
                }
            }
        }
        if (q!!.txQuery == null) q.txQuery = MoneroTxQuery()
        q.txQuery!!.transferQuery = q
        return q
    }

    protected fun announceSyncProgress(height: ULong, startHeight: ULong, endHeight: ULong, percentDone: Double, message: String) {
        for (listener in listeners) {
            try {
                listener.onSyncProgress(height, startHeight, endHeight, percentDone, message)
            } catch (e: Exception) {
                System.err.println("Error calling listener on sync progress: " + e.message)
                e.printStackTrace()
            }
        }
    }

    protected fun announceNewBlock(height: ULong) {
        for (listener in listeners) {
            try {
                listener.onNewBlock(height)
            } catch (e: Exception) {
                System.err.println("Error calling listener on new block: " + e.message)
                e.printStackTrace()
            }
        }
    }

    protected fun announceBalancesChanged(balance: ULong, unlockedBalance: ULong) {
        for (listener in listeners) {
            try {
                listener.onBalancesChanged(balance, unlockedBalance)
            } catch (e: Exception) {
                System.err.println("Error calling listener on balances changed: " + e.message)
                e.printStackTrace()
            }
        }
    }

    protected fun announceOutputReceived(output: MoneroOutputWallet) {
        for (listener in listeners) {
            try {
                listener.onOutputReceived(output)
            } catch (e: Exception) {
                System.err.println("Error calling listener on output received: " + e.message)
                e.printStackTrace()
            }
        }
    }

    protected fun announceOutputSpent(output: MoneroOutputWallet) {
        for (listener in listeners) {
            try {
                listener.onOutputSpent(output)
            } catch (e: Exception) {
                System.err.println("Error calling listener on output spent: " + e.message)
                e.printStackTrace()
            }
        }
    }

    override fun addListener(listener: MoneroWalletListener) {
        listeners.add(listener);
    }

    override fun removeListener(listener: MoneroWalletListener) {
        if (!listeners.contains(listener)) {
            throw MoneroError("Listener is not registered with wallet")
        }
    }

    override fun getListeners(): List<MoneroWalletListener> {
        return listeners.toList()
    }

    override fun setDaemonConnection(uri: String) {
        setDaemonConnection(uri, "", "")
    }

    override fun setDaemonConnection(uri: String, username: String, password: String) {
        if (uri.isEmpty()) {
            setDaemonConnection(null)
        }
        else {
            setDaemonConnection(MoneroRpcConnection(uri, username, password))
        }
    }

    override fun getPrimaryAddress(): String {
        return getAddress(0u, 0u)
    }

    override fun getIntegratedAddress(): MoneroIntegratedAddress {
        return getIntegratedAddress("", "")
    }

    override fun sync(): MoneroSyncResult {
        return sync(0u, null)
    }

    override fun sync(listener: MoneroWalletListener): MoneroSyncResult {
        return sync(0u, listener)
    }

    override fun sync(startHeight: ULong): MoneroSyncResult {
        return sync(startHeight, null)
    }

    override fun startSyncing() {
        startSyncing(0)
    }

    override fun getBalance(): ULong {
        return getBalance(null, null)
    }

    override fun getBalance(accountIdx: UInt): ULong {
        return getBalance(accountIdx, null)
    }

    override fun getUnlockedBalance(): ULong {
        return getUnlockedBalance(null, null)
    }

    override fun getUnlockedBalance(accountIdx: UInt): ULong {
        return getUnlockedBalance(accountIdx, null)
    }

    override fun getAccounts(): List<MoneroAccount> {
        return getAccounts(false, "")
    }

    override fun getAccounts(includeSubaddresses: Boolean): List<MoneroAccount> {
        return getAccounts(includeSubaddresses, "")
    }

    override fun getAccounts(tag: String): List<MoneroAccount> {
        return getAccounts(false, tag)
    }

    override fun createAccount(): MoneroAccount {
        return createAccount("")
    }

    override fun setAccountLabel(accountIdx: UInt, label: String) {
        setSubaddressLabel(accountIdx, 0u, label)
    }

    override fun getSubaddresses(accountIdx: UInt): List<MoneroSubaddress> {
        return getSubaddresses(accountIdx, emptyList())
    }

    override fun getSubaddress(accountIdx: UInt, subaddressIdx: UInt): MoneroSubaddress {
        val indices = ArrayList<UInt>()
        indices.add(accountIdx)
        val subaddresses = getSubaddresses(accountIdx, indices)
        if (subaddresses.isEmpty()) {
            throw MoneroError("Subaddress at index $subaddressIdx is not initialized")
        }
        if (subaddresses.size != 1) {
            throw MoneroError("Only 1 subaddress should be returned")
        }
        return subaddresses[0]
    }

    override fun createSubaddress(accountIdx: UInt): MoneroSubaddress {
        return createSubaddress(accountIdx, "")
    }

    override fun getTx(txHash: String): MoneroTxWallet? {
        val hashes = ArrayList<String>()
        hashes.add(txHash)
        val txs = getTxs(hashes)
        if (txs.isEmpty()) {
            return null
        }
        return txs[0]
    }

    override fun getTxs(): List<MoneroTxWallet> {
        return getTxs(MoneroTxQuery())
    }

    override fun getTxs(txHashes: List<String>): List<MoneroTxWallet> {
        val query = MoneroTxQuery()
        query.hashes = txHashes
        return getTxs(query)
    }

    override fun getTransfers(): List<MoneroTransfer> {
        return getTransfers(MoneroTransferQuery())
    }

    override fun getTransfers(accountIdx: UInt): List<MoneroTransfer> {
        val query = MoneroTransferQuery()
        query.accountIndex = accountIdx
        return getTransfers(query)
    }

    override fun getTransfers(accountIdx: UInt, subaddressIdx: UInt): List<MoneroTransfer> {
        val query = MoneroTransferQuery()
        query.accountIndex = accountIdx
        query.subaddressIndex = subaddressIdx
        return getTransfers(query)
    }

    override fun getIncomingTransfers(): List<MoneroIncomingTransfer> {
        return getIncomingTransfers(MoneroTransferQuery())
    }

    override fun getIncomingTransfers(query: MoneroTransferQuery): List<MoneroIncomingTransfer> {
        val q = normalizeTransferQuery(query)
        if (q.isIncoming == false) {
            throw MoneroError("Transfer query contradicts getting incoming transfers")
        }
        q.isIncoming = true
        val transfers = getTransfers(q)
        val inTransfers = ArrayList<MoneroIncomingTransfer>()
        for (transfer in transfers) {
            if (transfer !is MoneroIncomingTransfer) {
                throw MoneroError("Expected incoming transfers only")
            }
            inTransfers.add(transfer)
        }
        return inTransfers
    }

    override fun getOutgoingTransfers(): List<MoneroOutgoingTransfer> {
        return getOutgoingTransfers(MoneroTransferQuery())
    }

    override fun getOutgoingTransfers(query: MoneroTransferQuery): List<MoneroOutgoingTransfer> {
        val q = normalizeTransferQuery(query)
        if (q.isOutgoing == false) {
            throw MoneroError("Transfer query contradicts getting outgoing transfers")
        }
        q.isOutgoing = true
        val transfers = getTransfers(q)
        val outTransfers = ArrayList<MoneroOutgoingTransfer>()
        for (transfer in transfers) {
            if (transfer !is MoneroOutgoingTransfer) {
                throw MoneroError("Expected incoming transfers only")
            }
            outTransfers.add(transfer)
        }
        return outTransfers
    }

    override fun getOutputs(): List<MoneroOutput> {
        return getOutputs(MoneroOutputQuery())
    }

    override fun createTx(config: MoneroTxConfig): MoneroTxWallet {
        if (config.canSplit) {
            throw MoneroError("Cannot request split transactions with createTx() which prevents splitting; use createTxs() instead")
        }
        val copy = config.copy()
        copy.canSplit = false
        return createTxs(copy).first()
    }

    override fun relayTx(txMetadata: String): String {
        val metadatas = ArrayList<String>()
        metadatas.add(txMetadata)
        return relayTxs(metadatas).first()
    }

    override fun relayTx(tx: MoneroTxWallet): String {
        return relayTx(tx.metadata)
    }

    override fun relayTxs(txs: List<MoneroTxWallet>): List<String> {
        val txHexes = ArrayList<String>()
        for (tx in txs) {
            txHexes.add(tx.metadata)
        }
        return relayTxs(txHexes)
    }

    override fun describeUnsignedTxSet(unsignedTxHex: String): MoneroTxSet {
        val set = MoneroTxSet()
        set.unsignedTxHex = unsignedTxHex
        return describeTxSet(set)
    }

    override fun describeMultisigTxSet(multisigTxHex: String): MoneroTxSet {
        val set = MoneroTxSet()
        set.multisigTxHex = multisigTxHex
        return describeTxSet(set)
    }

    override fun signMessage(message: String): String {
        return signMessage(message, MoneroMessageSignatureType.SIGN_WITH_SPEND_KEY, 0u, 0u)
    }

    override fun getTxNote(txHash: String): String {
        val hashes = ArrayList<String>()
        hashes.add(txHash)
        return getTxNotes(hashes).first()
    }

    override fun setTxNote(txHash: String, note: String) {
        val hashes = ArrayList<String>()
        val notes = ArrayList<String>()
        hashes.add(txHash)
        notes.add(note)
        setTxNotes(hashes, notes)
    }

    override fun isMultisig(): Boolean {
        return getMultisigInfo().isMultisig
    }

    override fun importMultisigHex(multisigHex: String): Int {
        val hexes = ArrayList<String>()
        hexes.add(multisigHex)
        return importMultisigHexes(hexes)
    }

    override fun close(save: Boolean) {
        listeners.clear()
        isClosed = true
    }

    override fun isClosed(): Boolean {
        return isClosed
    }

}