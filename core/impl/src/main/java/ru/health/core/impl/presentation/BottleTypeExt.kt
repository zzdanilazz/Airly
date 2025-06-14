package ru.health.core.impl.presentation

import ru.health.core.api.domain.FlaconType
import ru.health.core.impl.R

val FlaconType.iconResId: Int
    get() = when (this) {
        FlaconType.SMALL -> R.drawable.ic_small_flacon
        FlaconType.TALL -> R.drawable.ic_tall_flacon
        FlaconType.LARGE -> R.drawable.ic_large_flacon
    }