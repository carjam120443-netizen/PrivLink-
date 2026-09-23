package com.privlink

import android.app.*
import android.content.Intent
import android.os.IBinder

class PrivilegedService : Service() {
    override fun onCreate() {
        super.onCreate()
        val channel = NotificationChannel("privlink", "PrivLink service", NotificationManager.IMPORTANCE_LOW)
        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        val notification = Notification.Builder(this, "privlink")
            .setContentTitle("PrivLink")
            .setContentText("Service is running")
            .setSmallIcon(android.R.drawable.stat_notify_sync)
            .build()
        startForeground(1001, notification)
    }
    override fun onBind(intent: Intent?): IBinder? = null
}
