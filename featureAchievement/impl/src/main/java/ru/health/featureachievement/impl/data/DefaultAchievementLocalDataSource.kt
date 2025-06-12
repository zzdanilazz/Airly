package ru.health.featureachievement.impl.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.health.featureachievement.api.data.AchievementLocalDataSource
import ru.health.featureachievement.api.domain.model.Achievement
import ru.health.featureachievement.api.domain.model.AchievementType
import ru.health.featureachievement.impl.R
import javax.inject.Inject

internal class DefaultAchievementLocalDataSource @Inject constructor(

) : AchievementLocalDataSource {

    private val achievementMap: Map<AchievementType, List<Achievement>> by lazy {
        mapOf(
            AchievementType.BASE to baseAchievements,
            AchievementType.ABSTINENCE_DURATION to abstinenceDurationAchievements,
            AchievementType.SAVED_MONEY to savedMoneyAchievements
        )
    }

    private val baseAchievements by lazy {
        listOf(
            Achievement(
                id = 1,
                name = "Добавить первое действие",
                imageResId = R.drawable.illustration_first_action,
                thresholdValue = 1
            ),
            Achievement(
                id = 2,
                name = "Поделиться прогрессом",
                imageResId = R.drawable.illustration_first_share,
                thresholdValue = 1

            ),
            Achievement(
                id = 3,
                name = "Указать уровень жидкости",
                imageResId = R.drawable.illustration_first_fill_liquid,
                thresholdValue = 1
            )
        )
    }

    private val abstinenceDurationAchievements by lazy {
        listOf(
            Achievement(
                id = 4,
                name = "Один день",
                imageResId = R.drawable.illustration_abstinence_1_day,
                level = 0,
                thresholdValue = 1
            ),
            Achievement(
                id = 5,
                name = "Два дня",
                imageResId = R.drawable.illustration_abstinence_2_day,
                level = 1,
                thresholdValue = 2
            ),
            Achievement(
                id = 6,
                name = "Три дня",
                imageResId = R.drawable.illustration_abstinence_3_day,
                level = 2,
                thresholdValue = 3
            ),
            Achievement(
                id = 7,
                name = "Одна неделя",
                imageResId = R.drawable.illustration_abstinence_7_day,
                level = 3,
                thresholdValue = 7
            ),
            Achievement(
                id = 8,
                name = "Полмесяца",
                imageResId = R.drawable.illustration_abstinence_14_day,
                level = 4,
                thresholdValue = 14
            ),
            Achievement(
                id = 9,
                name = "Один месяц",
                imageResId = R.drawable.illustration_abstinence_30_day,
                level = 5,
                thresholdValue = 30
            ),
            Achievement(
                id = 10,
                name = "Два месяца",
                imageResId = R.drawable.illustration_abstinence_60_day,
                level = 6,
                thresholdValue = 60
            ),
            Achievement(
                id = 11,
                name = "Три месяца",
                imageResId = R.drawable.illustration_abstinence_120_day,
                level = 7,
                thresholdValue = 120
            ),
            Achievement(
                id = 12,
                name = "Один год",
                imageResId = R.drawable.illustration_abstinence_365_day,
                level = 8,
                thresholdValue = 365
            )
        )
    }

    private val savedMoneyAchievements by lazy {
        val savedMoney: (Int) -> String = { "Сэкономлено $it руб" }
        listOf(
            Achievement(
                id = 13,
                name = savedMoney(1_000),
                imageResId = R.drawable.illustration_saved_money,
                level = 0,
                thresholdValue = 1_000
            ),
            Achievement(
                id = 14,
                name = savedMoney(2_000),
                imageResId = R.drawable.illustration_saved_money,
                level = 1,
                thresholdValue = 2_000
            ),
            Achievement(
                id = 15,
                name = savedMoney(3_000),
                imageResId = R.drawable.illustration_saved_money,
                level = 2,
                thresholdValue = 3_000
            ),
            Achievement(
                id = 16,
                name = savedMoney(5_000),
                imageResId = R.drawable.illustration_saved_money,
                level = 3,
                thresholdValue = 5_000
            ),
            Achievement(
                id = 17,
                name = savedMoney(10_000),
                imageResId = R.drawable.illustration_saved_money,
                level = 4,
                thresholdValue = 10_000
            ),
            Achievement(
                id = 18,
                name = savedMoney(20_000),
                imageResId = R.drawable.illustration_saved_money,
                level = 5,
                thresholdValue = 20_000
            )
        )
    }

    override suspend fun getAchievementMap(): Map<AchievementType, List<Achievement>> =
        withContext(Dispatchers.IO){
            achievementMap
        }
}
