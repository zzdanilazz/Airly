package ru.health.featureliquid.api.domain.usecase

import ru.health.core.api.ResultError
import ru.health.core.api.domain.result.RootResult

interface SaveConsumptionUseCase {

    suspend operator fun invoke(
        deviceId: Int,
        isMeasured: Boolean = false,
        vapeDuration: Float? = null,
        liquidDelta: Float? = null
    ): RootResult<Unit, ResultError>

}