package ru.health.featureachievement.api.domain.model

enum class AchievementType(
    val typeName: String,
    val units: String? = null
) {
    BASE(
        typeName = "Базовые"
    ),
    ABSTINENCE_DURATION(
        typeName = "Время воздержания",
        units = "дней"
    ),
    SAVED_MONEY(
        typeName = "Сэкономленные средства",
        units = "₽"
    )
}