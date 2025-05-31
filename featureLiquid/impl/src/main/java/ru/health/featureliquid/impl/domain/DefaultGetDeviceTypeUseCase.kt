package ru.health.featureliquid.impl.domain

import ru.health.core.api.RequestError
import ru.health.core.api.ResultError
import ru.health.core.api.domain.result.RootResult
import ru.health.featureliquid.api.domain.model.DeviceType
import ru.health.featureliquid.api.domain.usecase.GetDeviceTypeUseCase
import ru.health.featureliquid.impl.presentation.detail.ui.disposablePreview
import ru.health.featureliquid.impl.presentation.detail.ui.liquidPreview
import javax.inject.Inject

class DefaultGetDeviceTypeUseCase @Inject constructor(

) : GetDeviceTypeUseCase {

    override suspend fun invoke(isPod: Boolean?): RootResult<DeviceType, ResultError> = try {
        //todo для теста
        val deviceType = isPod?.let {
            if (isPod) {
                liquidPreview
            } else {
                disposablePreview
            }
        } ?: liquidPreview

        deviceType?.let { RootResult.Success(it) } ?: RootResult.Failure(RequestError.GENERIC)
    } catch (_: Exception) {
        RootResult.Failure(RequestError.GENERIC)
    }
}
