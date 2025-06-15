package ru.health.featuredashboard.impl.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.health.core.api.RequestError
import ru.health.core.api.ResultError
import ru.health.core.api.domain.result.RootResult
import ru.health.core.impl.domain.inDaysFloat
import ru.health.featuredashboard.api.domain.usecase.GetSavedMoneyFlowUseCase
import ru.health.featureliquid.api.domain.LiquidRepository
import ru.health.featureliquid.api.domain.model.Consumption
import ru.health.featureliquid.api.domain.model.Device
import javax.inject.Inject
import kotlin.time.Duration

class DefaultGetSavedMoneyFlowUseCase @Inject constructor(
    private val liquidRepository: LiquidRepository
) : GetSavedMoneyFlowUseCase {

    override suspend fun invoke(
        durationFlow: Flow<Duration>
    ): RootResult<Flow<Double>, ResultError> = try {
        val savedMoneyFlow = durationFlow.map {
            calculateSum(it, liquidRepository.getAllDevices())
        }
        RootResult.Success(savedMoneyFlow)
    } catch (_: Exception) {
        RootResult.Failure(RequestError.GENERIC)
    }
}

private fun calculateSum(
    totalDuration: Duration,
    deviceList: List<Device>
): Double {
    val weightedFrequencies = calculateWeightedFrequencies(
        deviceList.flatMap { it.consumptions }
    )
    val sum = weightedFrequencies.entries.sumOf { (deviceId, totalConsumption) ->
        val devicePrice = deviceList.find { it.id == deviceId }?.price?.toDouble() ?: 0.0
        val abstinencePeriod = totalDuration.inDaysFloat - totalConsumption.totalDuration
        abstinencePeriod * totalConsumption.totalFrequency * devicePrice
    }
    return sum
}

private fun calculateWeightedFrequencies(consumptions: List<Consumption>): Map<Int, TotalConsumption> {
    return consumptions
        .groupBy { it.deviceId }
        .mapValues { (_, group) ->
            val totalDevicesCount = group.sumOf { (it.frequency * it.durationInDays).toDouble() }
            val totalVapeDuration = group.sumOf { it.durationInDays.toDouble() }

            TotalConsumption(
                totalFrequency = if (totalVapeDuration == 0.0) {
                    0.0
                } else {
                    (totalDevicesCount / totalVapeDuration)
                },
                totalDuration = totalVapeDuration
            )
        }
}

private data class TotalConsumption(
    val totalFrequency: Double,
    val totalDuration: Double,
)




