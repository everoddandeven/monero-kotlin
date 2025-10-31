package org.monero.common

open class MoneroOutput {
    val tx: MoneroTx? = null
    val keyImage: MoneroKeyImage? = null
    val amount: ULong = 0u
    val index: ULong = 0u
    val ringOutputIndices: ArrayList<ULong> = ArrayList()
    val stealthPublicKey: String = ""
}
