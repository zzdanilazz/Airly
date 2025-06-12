package ru.health.featurenotifications.api.presentation

import android.app.Notification

interface NotificationProcess {

    fun createNotification(title: String, message: String): Notification

    fun notify(notification: Notification)
}
