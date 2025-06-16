package ru.health.featureliquid.api.domain.usecase

import ru.health.core.api.ResultError
import ru.health.core.api.domain.DeviceType
import ru.health.core.api.domain.result.RootResult
import ru.health.featureliquid.api.domain.model.FlaconParams

interface AddDeviceUseCase {

    suspend operator fun invoke(
        deviceType: DeviceType,
        flaconParams: FlaconParams? = null,
        price: Int,
    ): RootResult<Unit, ResultError>

}