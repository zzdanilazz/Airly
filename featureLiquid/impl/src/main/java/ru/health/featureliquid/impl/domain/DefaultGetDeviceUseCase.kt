package ru.health.featureliquid.impl.domain

import ru.health.core.api.RequestError
import ru.health.core.api.ResultError
import ru.health.core.api.domain.DeviceType
import ru.health.core.api.domain.result.RootResult
import ru.health.featureliquid.api.domain.model.Device
import ru.health.featureliquid.api.domain.usecase.GetDeviceUseCase
import ru.health.featureliquid.impl.presentation.detail.ui.disposablePreview
import ru.health.featureliquid.impl.presentation.detail.ui.liquidPreview
import javax.inject.Inject

class DefaultGetDeviceUseCase @Inject constructor(

) : GetDeviceUseCase {

    override suspend fun invoke(deviceType: DeviceType?): RootResult<Device, ResultError> = try {
        //todo для теста
        val device = deviceType?.let {
            if (it == DeviceType.POD) {
                liquidPreview
            } else {
                disposablePreview
            }
        } ?: liquidPreview

        device?.let { RootResult.Success(it) } ?: RootResult.Failure(RequestError.GENERIC)
    } catch (_: Exception) {
        RootResult.Failure(RequestError.GENERIC)
    }
}
