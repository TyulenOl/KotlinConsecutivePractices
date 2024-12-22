package com.example.kotlinconsecutivepractices.presentation.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.kotlinconsecutivepractices.R

class NotificationChannelManager(
    private val notificationManager: NotificationManagerCompat,
    context: Context
) {

    private val defaultChannel = ChannelInfo(
        id = context.resources.getString(R.string.notifications_channel),
        name = context.resources.getString(R.string.notifications_channel_name)
    )

    fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannelsSafety()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannelsSafety() {
        createChannel(defaultChannel)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel(channelInfo: ChannelInfo) {
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelInfo.id, channelInfo.name, importance).apply {
            enableLights(true)
            enableVibration(true)
            setShowBadge(true)
            lockscreenVisibility = NotificationCompat.VISIBILITY_PRIVATE
        }

        notificationManager.createNotificationChannel(channel)
    }
}

class ChannelInfo(
    val id: String,
    val name: String
)