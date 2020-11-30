package com.example.serviceexample

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi

class ForegroundService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        val pendingIntent: PendingIntent =
            Intent(this, MainActivity::class.java).let { notiIntent ->
                PendingIntent.getActivity(this, 0, notiIntent, 0)
            }

        val channerId: String = "channel1"
        val builder: Notification.Builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channerId,
                "my service channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
            Notification.Builder(this, channerId)
        } else {
            Notification.Builder(this)
        }

        builder.setContentTitle("다운로드 중")
            .setContentText("다운로드 중...")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentIntent(pendingIntent)
            .setTicker("ticker")
            .build()

        startForeground(1001, builder.build())
    }
}