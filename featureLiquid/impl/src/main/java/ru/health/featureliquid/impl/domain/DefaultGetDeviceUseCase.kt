package ru.health.featureliquid.impl.domain

import ru.health.core.api.RequestError
import ru.health.core.api.ResultError
import ru.health.core.api.domain.DeviceType
import ru.health.core.api.domain.result.RootResult
import ru.health.featureliquid.api.domain.LiquidRepository
import ru.health.featureliquid.api.domain.model.Device
import ru.health.featureliquid.api.domain.usecase.GetDeviceUseCase
import javax.inject.Inject

class DefaultGetDeviceUseCase @Inject constructor(
    private val liquidRepository: LiquidRepository
) : GetDeviceUseCase {

    override suspend fun invoke(deviceType: DeviceType?): RootResult<Device, ResultError> = try {
        val device = deviceType?.let {
            liquidRepository.getDeviceByTypeId(it.id)
        } ?: liquidRepository.getLatestDevice()

        device?.let { RootResult.Success(it) } ?: RootResult.Failure(RequestError.GENERIC)
    } catch (_: Exception) {
        RootResult.Failure(RequestError.GENERIC)
    }
}
