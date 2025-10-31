package org.monero.common

class MoneroRpcError(code: Int = -1, message: String) : MoneroError(message) {
}