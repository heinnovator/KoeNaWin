package com.heinnovator.koenawin

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.heinnovator.koenawin.notification.NotificationReceiver

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ){
            val channel = NotificationChannel(
                NotificationReceiver.CHANNEL_ID,
                "Reminder Notification",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.description = "Remind time to do"

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}