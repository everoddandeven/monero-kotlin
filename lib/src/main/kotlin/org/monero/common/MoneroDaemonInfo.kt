package org.monero.common

class MoneroDaemonInfo {
    var version: String = ""
    var numAltBlocks: ULong = 0u
    var blockSizeLimit: ULong = 0u
    var blockSizeMedian: ULong = 0u
    var blockWeightLimit: ULong = 0u
    var blockWeightMedian: ULong = 0u
    var bootstrapDaemonAddress: String = ""
    var difficulty: ULong = 0u
    var cumulativeDifficulty: ULong = 0u
    var freeSpace: ULong = 0u
    var numOfflinePeers: Int = 0
    var numOnlinePeers: Int = 0
    var height: ULong = 0u
    var heightWithoutBootstrap: ULong = 0u
    var networkType: MoneroNetworkType = MoneroNetworkType.MAINNET
    var isOffline: Boolean = true
    var numIncomingConnections: Int = 0
    var numOutgoingConnections: Int = 0
    var numRpcConnections: Int = 0
    var startTimestamp: ULong = 0u
    var adjustedTimestamp: ULong = 0u
    var target: ULong = 0u
    var targetHeight: ULong = 0u
    var topBlockHash: String = ""
    var numTxs: ULong = 0u
    var numTxsPool: ULong = 0u
    var wasBootstrapEverUsed: Boolean = false
    var databaseSize: ULong = 0u
    var updateAvailable: Boolean = false
    var credits: ULong = 0u
    var isBusySyncing: Boolean = false
    var isSynchronized: Boolean = false
    var isRestricted: Boolean = false
}