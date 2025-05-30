package ru.health.featurenotifications.api.domain

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.serialization.Serializable

@Serializable
data class Achievement(
    val id: Int,
    @StringRes val nameResId: Int,
    @StringRes val descriptionResId: Int,
    @DrawableRes val imageResId: Int
)
