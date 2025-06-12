package ru.health.featurenotifications.impl.presentation.push

import android.app.Notification
import android.app.NotificationChannel
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import ru.health.core.api.Factory
import ru.health.featurenotifications.impl.R
import ru.health.featurenotifications.api.presentation.NotificationProcess
import java.util.Date
import javax.inject.Inject

class NotificationProcessImpl @Inject constructor(
    private val context: Context,
    private val notificationChannelFactory: Factory<@JvmSuppressWildcards Boolean, @JvmSuppressWildcards NotificationChannel>,
) : NotificationProcess {

    private val notificationManager: NotificationManagerCompat by lazy {
        NotificationManagerCompat.from(context)
    }

    override fun createNotification(
        title: String,
        message: String
    ): Notification {
        val channel = notificationChannelFactory.create(true)
        notificationManager.createNotificationChannel(channel)

        return NotificationCompat.Builder(context, ChannelFactory.Companion.APP_CHANNEL_NAME)
            .setSmallIcon(R.drawable.ic_push_notification)
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText(message))
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)
            .setChannelId(channel.id)
            .build()
    }

    override fun notify(notification: Notification) {
        try {
            notificationManager.notify(null, Date().time.toInt(), notification)
        } catch (_: SecurityException) {
        }
    }
}
