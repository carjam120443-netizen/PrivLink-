package com.privlink

import android.os.Process

object AdbStarter {
    const val ADB_SHELL_UID = 2000
    fun isRunningAsAdbShell(): Boolean = Process.myUid() == ADB_SHELL_UID
    fun status(): String = if (isRunningAsAdbShell()) "ADB shell context detected" else "ADB bootstrap required"
}