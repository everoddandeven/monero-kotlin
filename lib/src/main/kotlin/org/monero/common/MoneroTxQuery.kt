package org.monero.common

class MoneroTxQuery : MoneroTx() {
    var isOutgoing: Boolean? = null
    var isIncoming: Boolean? = null
    var hashes: List<String> = emptyList()
    var hasPaymentId: Boolean? = null
    var paymentIds: List<String> = emptyList()
    var height: ULong? = null
    var minHeight: ULong? = null
    var maxHeight: ULong? = null
    var includeOutputs: Boolean? = null
    var transferQuery: MoneroTransferQuery? = null
    var inputQuery: MoneroOutputQuery? = null
    var outputQuery: MoneroOutputQuery? = null

    fun copy(): MoneroTxQuery {
        val txQuery = MoneroTxQuery()
        txQuery.isIncoming = isIncoming
        txQuery.isOutgoing = isOutgoing
        txQuery.hashes = hashes
        txQuery.hasPaymentId = hasPaymentId
        txQuery.paymentIds = paymentIds
        txQuery.minHeight = minHeight
        txQuery.maxHeight = maxHeight
        txQuery.includeOutputs = includeOutputs
        txQuery.transferQuery = transferQuery
        txQuery.inputQuery = inputQuery
        txQuery.outputQuery = outputQuery
        return txQuery
    }
}