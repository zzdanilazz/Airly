package ru.health.featurestatistics.impl.presentation.model

import androidx.annotation.DrawableRes
import ru.health.featurestatistics.impl.R

enum class GraphType(
    @DrawableRes val iconRes: Int
) {
    SAVED_MONEY(R.drawable.ic_money),
    HEALTH(R.drawable.ic_heart)
}