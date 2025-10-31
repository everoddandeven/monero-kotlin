package org.monero.wallet

import org.monero.common.MoneroAccount
import org.monero.common.MoneroAccountTag
import org.monero.common.MoneroAddressBookEntry
import org.monero.common.MoneroCheckReserve
import org.monero.common.MoneroCheckTx
import org.monero.common.MoneroIncomingTransfer
import org.monero.common.MoneroIntegratedAddress
import org.monero.common.MoneroKeyImage
import org.monero.common.MoneroKeyImageImportResult
import org.monero.common.MoneroMessageSignatureType
import org.monero.common.MoneroMultisigInfo
import org.monero.common.MoneroMultisigSignResult
import org.monero.common.MoneroOutgoingTransfer
import org.monero.common.MoneroOutput
import org.monero.common.MoneroOutputQuery
import org.monero.common.MoneroRpcConnection
import org.monero.common.MoneroSubaddress
import org.monero.common.MoneroSyncResult
import org.monero.common.MoneroTransfer
import org.monero.common.MoneroTransferQuery
import org.monero.common.MoneroTxConfig
import org.monero.common.MoneroTxPriority
import org.monero.common.MoneroTxQuery
import org.monero.common.MoneroTxSet
import org.monero.common.MoneroTxWallet
import org.monero.common.MoneroVersion

interface MoneroWallet {
    fun addListener(listener: MoneroWalletListener)
    fun removeListener(listener: MoneroWalletListener)
    fun getListeners(): List<MoneroWalletListener>
    fun isViewOnly(): Boolean
    fun setDaemonConnection(uri: String)
    fun setDaemonConnection(uri: String, username: String, password: String)
    fun setDaemonConnection(connection: MoneroRpcConnection?)
    fun getDaemonConnection(): MoneroRpcConnection
    fun isConnectedToDaemon(): Boolean
    fun getVersion(): MoneroVersion
    fun getPath(): String
    fun getSeed(): String
    fun getSeedLanguage(): String
    fun getPrivateViewKey(): String
    fun getPrivateSpendKey(): String
    fun getPublicSpendKey(): String
    fun getPublicViewKey(): String
    fun getPrimaryAddress(): String
    fun getAddress(accountIdx: UInt, subaddressIdx: UInt): String
    fun getAddressIndex(address: String): MoneroSubaddress
    fun getIntegratedAddress(): MoneroIntegratedAddress
    fun getIntegratedAddress(standardAddress: String, paymentId: String): MoneroIntegratedAddress
    fun decodeIntegratedAddress(integratedAddress: String): MoneroIntegratedAddress
    fun getHeight(): ULong
    fun getDaemonHeight(): ULong
    fun getHeightByDate(year: Int, month: Int, day: Int): ULong
    fun sync(): MoneroSyncResult
    fun sync(listener: MoneroWalletListener): MoneroSyncResult
    fun sync(startHeight: ULong): MoneroSyncResult
    fun sync(startHeight: ULong, listener: MoneroWalletListener?): MoneroSyncResult
    fun startSyncing()
    fun startSyncing(syncPeriodInMs: Long);
    fun stopSyncing()
    fun scanTxs(txHashes: List<String>)
    fun rescanSpent()
    fun getBalance(): ULong
    fun getBalance(accountIdx: UInt): ULong
    fun getBalance(accountIdx: UInt?, subaddressIdx: UInt?): ULong
    fun getUnlockedBalance(): ULong
    fun getUnlockedBalance(accountIdx: UInt): ULong
    fun getUnlockedBalance(accountIdx: UInt?, subaddressIdx: UInt?): ULong
    fun getAccounts(): List<MoneroAccount>
    fun getAccounts(includeSubaddresses: Boolean = false): List<MoneroAccount>
    fun getAccounts(tag: String): List<MoneroAccount>
    fun getAccounts(includeSubaddresses: Boolean, tag: String): List<MoneroAccount>
    fun getAccount(accountIdx: UInt, includeSubaddresses: Boolean = false): MoneroAccount
    fun createAccount(): MoneroAccount
    fun createAccount(label: String): MoneroAccount
    fun setAccountLabel(accountIdx: UInt, label: String)
    fun getSubaddresses(accountIdx: UInt): List<MoneroSubaddress>
    fun getSubaddresses(accountIdx: UInt, subaddressIndices: List<UInt>): List<MoneroSubaddress>
    fun getSubaddress(accountIdx: UInt, subaddressIdx: UInt): MoneroSubaddress
    fun createSubaddress(accountIdx: UInt): MoneroSubaddress
    fun createSubaddress(accountIdx: UInt, label: String): MoneroSubaddress
    fun setSubaddressLabel(accountIdx: UInt, subaddressIdx: UInt, label: String)
    fun getTx(txHash: String): MoneroTxWallet?
    fun getTxs(): List<MoneroTxWallet>
    fun getTxs(txHashes: List<String>): List<MoneroTxWallet>
    fun getTxs(query: MoneroTxQuery): List<MoneroTxWallet>
    fun getTransfers(): List<MoneroTransfer>
    fun getTransfers(accountIdx: UInt): List<MoneroTransfer>
    fun getTransfers(accountIdx: UInt, subaddressIdx: UInt): List<MoneroTransfer>
    fun getTransfers(query: MoneroTransferQuery): List<MoneroTransfer>
    fun getIncomingTransfers(): List<MoneroIncomingTransfer>
    fun getIncomingTransfers(query: MoneroTransferQuery): List<MoneroIncomingTransfer>
    fun getOutgoingTransfers(): List<MoneroOutgoingTransfer>
    fun getOutgoingTransfers(query: MoneroTransferQuery): List<MoneroOutgoingTransfer>
    fun getOutputs(): List<MoneroOutput>
    fun getOutputs(query: MoneroOutputQuery): List<MoneroOutput>
    fun exportOutputs(): String
    fun exportOutputs(all: Boolean): String
    fun importOutputs(outputsHex: String): Int
    fun exportKeyImages(all: Boolean = false): List<MoneroKeyImage>
    fun importKeyImages(keyImages: List<MoneroKeyImage>): MoneroKeyImageImportResult
    fun getNewKeyImagesFromLastImport(): List<MoneroKeyImage>
    fun freezeOutput(keyImage: String)
    fun thawOutput(keyImage: String)
    fun isOutputFrozen(keyImage: String): Boolean
    fun getDefaultFeePriority(): MoneroTxPriority
    fun createTx(config: MoneroTxConfig): MoneroTxWallet
    fun createTxs(config: MoneroTxConfig): List<MoneroTxWallet>
    fun sweepOutput(config: MoneroTxConfig): MoneroTxWallet
    fun sweepUnlocked(config: MoneroTxConfig): List<MoneroTxWallet>
    fun sweepDust(relay: Boolean): List<MoneroTxWallet>
    fun relayTx(txMetadata: String): String
    fun relayTx(tx: MoneroTxWallet): String
    fun relayTxs(txs: List<MoneroTxWallet>): List<String>
    fun relayTxs(txMetadata: List<String>): List<String>
    fun describeUnsignedTxSet(unsignedTxHex: String): MoneroTxSet
    fun describeMultisigTxSet(multisigTxHex: String): MoneroTxSet
    fun describeTxSet(txSet: MoneroTxSet): MoneroTxSet
    fun signTxs(unsignedTxHex: String): MoneroTxSet
    fun submitTxs(signedTxHex: String): List<String>
    fun signMessage(message: String): String
    fun signMessage(message: String, signatureType: MoneroMessageSignatureType, accountIdx: UInt, subaddressIdx: UInt): String
    fun verifyMessage(message: String, address: String, signature: String): String
    fun getTxKey(txHash: String): String
    fun checkTxKey(txHash: String, txKey: String, address: String): MoneroCheckTx
    fun getTxProof(txHash: String, address: String, message: String = ""): String
    fun checkTxProof(txHash: String, address: String, message: String, signature: String): MoneroCheckTx
    fun getSpendProof(txHash: String, message: String = ""): String
    fun checkSpendProof(txHash: String, message: String, signature: String): Boolean
    fun getReserveProofWallet(message: String): String
    fun getReserveProofAccount(accountIdx: UInt, amount: ULong, message: String): String
    fun checkReserveProof(address: String, message: String, signature: String): MoneroCheckReserve
    fun getTxNote(txHash: String): String
    fun getTxNotes(txHashes: List<String>): List<String>
    fun setTxNote(txHash: String, note: String)
    fun setTxNotes(txHashes: List<String>, notes: List<String>)
    fun getAddressBookEntries(entryIndices: List<Int>? = null): List<MoneroAddressBookEntry>
    fun getAddressBookEntry(address: String, description: String): Int
    fun editAddressBookEntry(index: Int, setAddress: Boolean, address: String, setDescription: Boolean, description: String)
    fun deleteAddressBookEntry(entryIdx: Int)
    fun tagAccounts(tag: String, accountIndices: List<UInt>)
    fun untagAccounts(accountIndices: List<UInt>)
    fun getAccountTags(): List<MoneroAccountTag>
    fun setAccountTagLabel(tag: String, label: String)
    fun getPaymentUri(config: MoneroTxConfig): String
    fun parsePaymentUri(uri: String): MoneroTxConfig
    fun getAttribute(key: String): String
    fun setAttribute(key: String, value: String)
    fun startMining(numThreads: Int, backgroundMining: Boolean, ignoreBattery: Boolean)
    fun stopMining()
    fun isMultisigImportNeeded(): Boolean
    fun isMultisig(): Boolean
    fun getMultisigInfo(): MoneroMultisigInfo
    fun prepareMultisig(): String
    fun makeMultisig(multisigHexes: List<String>, threshold: Int, password: String): String
    fun exchangeMultisigKeys(multisigHexes: List<String>, password: String): String
    fun exportMultisigHex(): String
    fun importMultisigHex(multisigHex: String): Int
    fun importMultisigHexes(multisigHexes: List<String>): Int
    fun signMultisigTxHex(multisigTxHex: String): MoneroMultisigSignResult
    fun submitMultisigTxHex(signedMultisigTxHex: String): List<String>
    fun changePassword(oldPassword: String, newPassword: String)
    fun isClosed(): Boolean
    fun close(save: Boolean = false)
    fun save()
}