package ru.health.featureliquid.api.data

import java.util.Date

data class VapeActionData(
    val id: Int = 0,
    val puffs: Int,
    val deviceId: Int,
    val date: Date
)