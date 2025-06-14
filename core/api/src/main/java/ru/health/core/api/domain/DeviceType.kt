package ru.health.core.api.domain

enum class DeviceType(
    val id: Int,
    val isPrimary: Boolean
) {
    POD(
        id = 1,
        isPrimary = true
    ),
    DISPOSABLE(
        id = 2,
        isPrimary = true
    ),
    VAPORIZER(
        id = 3,
        isPrimary = false
    )
}