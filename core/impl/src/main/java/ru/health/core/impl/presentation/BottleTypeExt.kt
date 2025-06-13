package ru.health.core.impl.presentation

import ru.health.core.api.domain.BottleType
import ru.health.core.impl.R

val BottleType.iconResId: Int
    get() = when (this) {
        BottleType.SMALL -> R.drawable.ic_small_flacon
        BottleType.TALL -> R.drawable.ic_tall_flacon
        BottleType.LARGE -> R.drawable.ic_large_flacon
    }