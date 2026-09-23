package com.privlink

import java.io.BufferedReader
import java.io.InputStreamReader

object RootStarter {
    data class Result(val available: Boolean, val identity: String)

    fun check(): Result {
        return try {
            val process = ProcessBuilder("su", "-c", "id")
                .redirectErrorStream(true)
                .start()
            val output = BufferedReader(InputStreamReader(process.inputStream)).use { it.readLine().orEmpty() }
            val code = process.waitFor()
            Result(code == 0 && output.contains("uid=0"), output.ifBlank { "su unavailable" })
        } catch (_: Exception) {
            Result(false, "su unavailable")
        }
    }
}