package com.privlink

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                var running by remember { mutableStateOf(false) }
                var status by remember { mutableStateOf(BackendStatus.read()) }

                Column(
                    Modifier.fillMaxSize().padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(18.dp)
                ) {
                    Text("PrivLink", style = MaterialTheme.typography.headlineMedium)
                    Text("Privileged service manager")

                    Card(Modifier.fillMaxWidth()) {
                        Column(Modifier.padding(20.dp)) {
                            Text("Service", style = MaterialTheme.typography.titleMedium)
                            Text(if (running) "● Running" else "○ Stopped")
                        }
                    }

                    Card(Modifier.fillMaxWidth()) {
                        Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text("Backends", style = MaterialTheme.typography.titleMedium)
                            Text(if (status.rootAvailable) "✓ Root available" else "○ Root unavailable")
                            Text("Root identity: " + status.rootIdentity)
                            Text(if (status.adbShell) "✓ ADB shell context" else "○ ADB bootstrap required")
                        }
                    }

                    Button(
                        Modifier.fillMaxWidth(),
                        onClick = {
                            ContextCompat.startForegroundService(
                                this@MainActivity,
                                Intent(this@MainActivity, PrivilegedService::class.java)
                            )
                            running = true
                            status = BackendStatus.read()
                        }
                    ) { Text("Start service") }

                    OutlinedButton(
                        Modifier.fillMaxWidth(),
                        onClick = {
                            stopService(Intent(this@MainActivity, PrivilegedService::class.java))
                            running = false
                        }
                    ) { Text("Stop service") }

                    OutlinedButton(
                        Modifier.fillMaxWidth(),
                        onClick = { status = BackendStatus.read() }
                    ) { Text("Refresh backend status") }
                }
            }
        }
    }
}