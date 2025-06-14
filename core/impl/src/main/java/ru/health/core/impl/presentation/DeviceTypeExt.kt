package ru.health.core.impl.presentation

import ru.health.core.api.domain.DeviceType
import ru.health.core.impl.R

val DeviceType.iconResId: Int
    get() = when (this) {
        DeviceType.POD -> R.drawable.ic_pod
        DeviceType.DISPOSABLE -> R.drawable.ic_disposable
        DeviceType.VAPORIZER -> R.drawable.ic_cartridge
    }

val DeviceType.titleResId: Int
    get() = when (this) {
        DeviceType.POD -> R.string.pod
        DeviceType.DISPOSABLE -> R.string.disposable
        DeviceType.VAPORIZER -> R.string.vaporizer
    }