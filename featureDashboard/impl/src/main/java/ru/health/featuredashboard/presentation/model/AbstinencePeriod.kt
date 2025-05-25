package ru.health.featuredashboard.presentation.model

import kotlin.time.Duration

data class AbstinencePeriod(
    val duration: Duration
) {
    val timeUnits: List<Pair<Int, String>>
        get() {
            val days = duration.inWholeDays.toInt()
            val hours = (duration.inWholeHours % 24).toInt()
            val minutes = (duration.inWholeMinutes % 60).toInt()
            val seconds = (duration.inWholeSeconds % 60).toInt()

            val units = mutableListOf<Pair<Int, String>>()

            if (days > 0) units.add(days to formatTimeUnit(days, "день", "дня", "дней"))
            if (hours > 0) units.add(hours to formatTimeUnit(hours, "час", "часа", "часов"))
            if (minutes > 0) units.add(minutes to formatTimeUnit(minutes, "минута", "минуты", "минут"))
            units.add(seconds to formatTimeUnit(seconds, "секунда", "секунды", "секунд"))

            return units
        }

    private fun formatTimeUnit(
        value: Int,
        one: String,
        few: String,
        many: String
    ): String {
        return when {
            value % 10 == 1 && value % 100 != 11 -> one
            value % 10 in 2..4 && value % 100 !in 12..14 -> few
            else -> many
        }
    }

    companion object {
        val Zero = AbstinencePeriod(Duration.ZERO)
    }
}