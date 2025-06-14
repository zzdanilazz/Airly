package ru.health.core.api.domain

enum class FlaconType(
    val id: Int,
    val volume: Float
) {
    SMALL(
        id = 1,
        volume = 30f
    ),
    TALL(
        id = 2,
        volume = 60f
    ),
    LARGE(
        id = 3,
        volume = 120f
    )
}