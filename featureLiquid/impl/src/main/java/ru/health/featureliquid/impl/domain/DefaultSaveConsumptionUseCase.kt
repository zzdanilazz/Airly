package ru.health.featureliquid.impl.domain

import ru.health.core.api.RequestError
import ru.health.core.api.ResultError
import ru.health.core.api.data.date.DateFormatter
import ru.health.core.api.domain.DeviceType
import ru.health.core.api.domain.result.RootResult
import ru.health.featureliquid.api.domain.LiquidRepository
import ru.health.featureliquid.api.domain.model.Consumption
import ru.health.featureliquid.api.domain.model.Device
import ru.health.featureliquid.api.domain.usecase.SaveConsumptionUseCase
import java.util.Date
import javax.inject.Inject

class DefaultSaveConsumptionUseCase @Inject constructor(
    private val liquidRepository: LiquidRepository,
    private val dateFormatter: DateFormatter
) : SaveConsumptionUseCase {

    override suspend fun invoke(
        deviceId: Int,
        isMeasured: Boolean,
        vapeDuration: Float?,
        liquidDelta: Float?
    ): RootResult<Unit, ResultError> = try {
        val device = liquidRepository.getDeviceById(deviceId)
        device?.let {
            val frequency = if (isMeasured && liquidDelta != null) {
                calculateFrequency(device, liquidDelta)
            } else {
                device.consumptions.maxByOrNull { it.id }?.frequency ?: getLastFrequency(device.deviceType)
            }
            val consumption = Consumption(
                frequency = frequency,
                durationInDays = if (isMeasured) 0f else vapeDuration ?: calculateDuration(device, frequency),
                isMeasured = isMeasured,
                deviceId = device.id,
                date = dateFormatter.formatDate(Date())
            )

            RootResult.Success(liquidRepository.saveConsumption(consumption))
        } ?:  RootResult.Failure(RequestError.GENERIC)
    } catch (_: Exception) {
        RootResult.Failure(RequestError.GENERIC)
    }

    private suspend fun calculateFrequency(device: Device, liquidDelta: Float): Float {
        val notMeasuredConsumptions = device.consumptions.takeUntilLastMeasured()
        val totalDuration = device.consumptions.sumOf { it.durationInDays.toDouble() }
        val measuredFrequency = (liquidDelta / totalDuration).toFloat()
        liquidRepository.updateConsumptions(
            notMeasuredConsumptions.map { it.copy(frequency = measuredFrequency) }
        )

        return measuredFrequency
    }

    private fun List<Consumption>.takeUntilLastMeasured(): List<Consumption> {
        val reversed = this.asReversed()
        val index = reversed.indexOfFirst { it.isMeasured }

        return when (index) {
            -1 -> emptyList()
            0 -> reversed.drop(1).asReversed().takeUntilLastMeasured()
            else -> reversed.take(index).asReversed()
        }
    }

    private suspend fun getLastFrequency(deviceType: DeviceType): Float {
        val allDevices = liquidRepository.getAllDevices().filter { it.deviceType == deviceType }
        val device = allDevices.getOrNull(allDevices.size - 2)
        return device?.consumptions?.lastOrNull()?.frequency ?: throw NullPointerException()
    }

    private fun calculateDuration(device: Device, frequency: Float): Float {
        val spentDeviceFraction =  device.consumptions.sumOf {
            it.frequency.toDouble() * it.durationInDays
        }
        val remainingDeviceFraction = 1 - spentDeviceFraction
        return remainingDeviceFraction.toFloat() * frequency
    }
}
