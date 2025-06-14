package ru.health.featureliquid.api.domain.usecase

import ru.health.core.api.ResultError
import ru.health.core.api.domain.DeviceType
import ru.health.core.api.domain.result.RootResult
import ru.health.core.api.domain.Device

interface GetDeviceUseCase {

    suspend operator fun invoke(deviceType: DeviceType? = null): RootResult<Device, ResultError>

}