package ru.health.featurestatistics.impl.domain

import ru.health.core.api.RequestError
import ru.health.core.api.ResultError
import ru.health.core.api.domain.result.RootResult
import ru.health.featureliquid.api.domain.LiquidRepository
import ru.health.featureliquid.api.domain.model.Device
import ru.health.featurestatistics.api.domain.GetAllDevicesUseCase
import java.util.Date
import javax.inject.Inject

class DefaultGetAllDevicesUseCase @Inject constructor(
    private val liquidRepository: LiquidRepository
) : GetAllDevicesUseCase {

    override suspend fun invoke(
        startDate: Date?,
        endDate: Date?
    ): RootResult<List<Device>, ResultError> = try {
        val devices = liquidRepository.getAllDevices(startDate, endDate)
        RootResult.Success(devices)
    } catch (_: Exception) {
        RootResult.Failure(RequestError.GENERIC)
    }
}
