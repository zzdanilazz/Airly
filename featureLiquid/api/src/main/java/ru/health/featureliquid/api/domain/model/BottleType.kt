package ru.health.featureliquid.api.domain.model

enum class BottleType(val volume: Float) {
    SMALL(30f),
    TALL(60f),
    LARGE(120f)
}