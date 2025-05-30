package ru.health.featurenotifications.impl.presentation.push

import android.app.NotificationChannel
import android.app.NotificationManager
import ru.health.core.api.Factory
import javax.inject.Inject

class ChannelFactory @Inject constructor() : Factory<Boolean, NotificationChannel> {

    override fun create(item: Boolean): NotificationChannel {
        return NotificationChannel(
            APP_CHANNEL_NAME,
            APP_CHANNEL_DESCRIPTION,
            NotificationManager.IMPORTANCE_DEFAULT
        ).also {
            it.description = APP_CHANNEL_DESCRIPTION
            it.setShowBadge(false)
        }
    }

    companion object {
        const val APP_CHANNEL_NAME = "ru.health.airly"
        const val APP_CHANNEL_DESCRIPTION = "Уведомления Airly"
    }
}
