package ru.health.featuredashboard.impl.presentation.startup_parameters

import androidx.compose.runtime.Immutable
import ru.health.core.api.domain.DeviceType
import ru.health.core.api.domain.FlaconParams
import ru.health.featuredashboard.impl.presentation.startup_parameters.model.Interest

@Immutable
internal data class StartupParametersUiState(
    val deviceType: DeviceType? = null,

    val pricePerDevice: String = "",
    val deviceBuyPeriod: String = "",

    val pricePerVaporizer: String = "",
    val vaporizerBuyPeriod: String = "",

    val flaconParams: FlaconParams? = null,

    val interests: List<Interest> = listOf(
        Interest("Путешествия"),
        Interest("Спорт"),
        Interest("Кино"),
        Interest("Технологии"),
        Interest("Кулинария"),
        Interest("Мода"),
        Interest("Автомобили"),
        Interest("Игры"),
        Interest("Музыка"),
        Interest("Книги"),
        Interest("Фитнес"),
        Interest("Фотография"),
        Interest("Здоровье"),
        Interest("Бизнес"),
        Interest("Дизайн"),
        Interest("Криптовалюты"),
        Interest("Саморазвитие"),
        Interest("Ремонт"),
        Interest("Аниме"),
        Interest("Гаджеты")
    )
) {
    val areFieldsFilled: Boolean
        get() = pricePerDevice.isNotBlank() && deviceBuyPeriod.isNotBlank()
                && (deviceType == DeviceType.DISPOSABLE || (pricePerVaporizer.isNotBlank()
                && vaporizerBuyPeriod.isNotBlank()))
}

