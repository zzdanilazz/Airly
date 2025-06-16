package ru.health.featurestatistics.impl.presentation

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ir.ehsannarmani.compose_charts.models.Bars
import ir.ehsannarmani.compose_charts.models.Line
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.health.core.api.presentation.component.ComponentViewModel
import ru.health.core.impl.presentation.ui.theme.LightRed
import ru.health.core.impl.presentation.ui.theme.PaleRed
import ru.health.featurestatistics.impl.domain.DefaultGetAllDevicesUseCase
import ru.health.featurestatistics.impl.presentation.model.GraphType
import ru.health.featurestatistics.impl.presentation.model.Period
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoField
import java.util.Date

internal class StatisticsViewModel @AssistedInject constructor(
    private val getAllDevicesUseCase: DefaultGetAllDevicesUseCase
) : ComponentViewModel() {

    private val _state = MutableStateFlow(StatisticsUiState())
    val state: StateFlow<StatisticsUiState> = _state.asStateFlow()

    private val _navEvent = Channel<StatisticsNavEvent>()
    val navEvent = _navEvent.receiveAsFlow()

    fun onAction(action: StatisticsAction) = launch {
        when (action) {
            StatisticsAction.Init -> init()
            StatisticsAction.Back -> back()
            is StatisticsAction.ChangePeriod -> changePeriod(action.period)
            is StatisticsAction.ChangeGraphType -> changeGraphType(action.graphType)
        }
    }

    private suspend fun init() {
        val now = LocalDateTime.now()
        val startDate = when (_state.value.period) {
            Period.DAY -> now.toLocalDate().atStartOfDay()
            Period.WEEK -> now
                .with(ChronoField.DAY_OF_WEEK, 1)
                .toLocalDate()
                .atStartOfDay()
            Period.MONTH -> now
                .withDayOfMonth(1)
                .toLocalDate()
                .atStartOfDay()
            Period.YEAR -> now
                .withDayOfYear(1)
                .toLocalDate()
                .atStartOfDay()
        }
        val endDate = now

        val zoneId = ZoneId.systemDefault()
        val startDateInstant = startDate.atZone(zoneId).toInstant()
        val endDateInstant = endDate.atZone(zoneId).toInstant()

        val startDateDate = Date.from(startDateInstant)
        val endDateDate = Date.from(endDateInstant)
        getAllDevicesUseCase(startDateDate, endDateDate).onSuccess { devices ->
            _state.update { uiState ->
                val lostMoneyData = devices.flatMap { device ->
                    device.consumptions
                        .filter { it.durationInDays > 0 }
                        .map { consumption ->
                            Bars(
                                label = consumption.date,
                                values = listOf(
                                    Bars.Data(
                                        id = consumption.id,
                                        value = consumption.durationInDays.toDouble() *
                                                consumption.frequency * device.price,
                                        color = Brush.verticalGradient(
                                            listOf(
                                                LightRed,
                                                PaleRed
                                            )
                                        )
                                    )
                                )
                            )
                    }
                }
                val lostFrequencyData = listOf(
                    Line(
                        label = devices.size.toString(),
                        values = devices
                            .flatMap { it.consumptions }
                            .filter { it.durationInDays > 0 }
                            .map { it.frequency.toDouble() },
                        color = SolidColor(LightRed),
                        firstGradientFillColor = LightRed.copy(alpha = 0.5f),
                        secondGradientFillColor = Color.Transparent
                    )
                )
                uiState.copy(lostMoneyData = lostMoneyData, lostFrequencyData = lostFrequencyData)
            }
        }
    }

    private fun changePeriod(period: Period) {
        _state.update { it.copy(period = period) }
    }

    private fun changeGraphType(graphType: GraphType) {
        _state.update { it.copy(graphType = graphType) }
    }

    private suspend fun back() {
        _navEvent.send(StatisticsNavEvent.Back)
    }

    @AssistedFactory
    interface Factory {
        operator fun invoke(): StatisticsViewModel
    }
}