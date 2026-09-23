package com.privlink

class PrivilegedBinder : IPrivilegedService.Stub() {
    private val root = RootStarter.check()

    override fun getBackend(): String =
        if (root.available) "root" else if (AdbStarter.isRunningAsAdbShell()) "adb" else "app"

    override fun getIdentity(): String =
        if (root.available) root.identity else AdbStarter.status()

    override fun isPrivileged(): Boolean =
        root.available || AdbStarter.isRunningAsAdbShell()
}