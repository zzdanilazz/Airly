package ru.health.featureliquid.impl.domain

import ru.health.core.api.RequestError
import ru.health.core.api.ResultError
import ru.health.core.api.data.date.DateFormatter
import ru.health.core.api.domain.DeviceType
import ru.health.core.api.domain.result.RootResult
import ru.health.featureliquid.api.domain.LiquidRepository
import ru.health.featureliquid.api.domain.model.Device
import ru.health.featureliquid.api.domain.model.FlaconParams
import ru.health.featureliquid.api.domain.usecase.AddDeviceUseCase
import java.util.Date
import javax.inject.Inject

class DefaultAddDeviceUseCase @Inject constructor(
    private val liquidRepository: LiquidRepository,
    private val dateFormatter: DateFormatter
) : AddDeviceUseCase {

    override suspend fun invoke(
        deviceType: DeviceType,
        flaconParams: FlaconParams?,
        price: Int
    ): RootResult<Unit, ResultError> = try {
        val device = Device(
            deviceType = deviceType,
            flaconParams = flaconParams,
            price = price,
            date = dateFormatter.formatDate(Date()),
        )
        liquidRepository.saveDevice(device)
        RootResult.Success(Unit)
    } catch (_: Exception) {
        RootResult.Failure(RequestError.GENERIC)
    }
}
