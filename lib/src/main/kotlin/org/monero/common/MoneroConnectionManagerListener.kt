package org.monero.common

interface MoneroConnectionManagerListener {
    fun onConnectionChanged(connection: MoneroRpcConnection)
}