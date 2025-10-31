package org.monero.common

open class MoneroError(message: String) : RuntimeException(message) {
    var code: Int? = null
}