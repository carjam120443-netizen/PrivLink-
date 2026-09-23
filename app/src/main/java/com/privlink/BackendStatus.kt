package com.privlink

data class BackendStatus(
    val rootAvailable: Boolean,
    val rootIdentity: String,
    val adbShell: Boolean
) {
    companion object {
        fun read(): BackendStatus {
            val root = RootStarter.check()
            return BackendStatus(root.available, root.identity, AdbStarter.isRunningAsAdbShell())
        }
    }
}