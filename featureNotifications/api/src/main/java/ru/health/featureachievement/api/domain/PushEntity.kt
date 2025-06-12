package ru.health.featurenotifications.api.domain

data class PushEntity(
    val pushId: Int,
    val title: String,
    val message: String,
    val date: String
)
