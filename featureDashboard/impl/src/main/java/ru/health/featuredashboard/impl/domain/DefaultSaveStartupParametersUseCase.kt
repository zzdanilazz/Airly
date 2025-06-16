package ru.health.featuredashboard.impl.domain

import ru.health.core.api.RequestError
import ru.health.core.api.ResultError
import ru.health.core.api.data.MetaDataStore
import ru.health.core.api.data.date.DateFormatter
import ru.health.core.api.domain.result.RootResult
import ru.health.featuredashboard.api.domain.DashboardRepository
import ru.health.featuredashboard.api.domain.model.StartupParameters
import ru.health.featuredashboard.api.domain.usecase.SaveStartupParametersUseCase
import ru.health.featureliquid.api.domain.LiquidRepository
import ru.health.featureliquid.api.domain.model.Consumption
import java.util.Date
import javax.inject.Inject

class DefaultSaveStartupParametersUseCase @Inject constructor(
    private val dashboardRepository: DashboardRepository,
    private val liquidRepository: LiquidRepository,
    private val metaDataStore: MetaDataStore,
    private val dateFormatter: DateFormatter
) : SaveStartupParametersUseCase {

    override suspend fun invoke(startupParameters: StartupParameters): RootResult<Unit, ResultError> = try {
        with (startupParameters) {
            val primaryDeviceId = liquidRepository.saveDevice(primaryDevice)
            val secondaryDeviceId = secondaryDevice?.let {
                liquidRepository.saveDevice(it)
            }
            val currentDate = dateFormatter.formatDate(Date())

            val primaryDeviceConsumeFrequency = 1f / primaryDeviceBuyPeriod
            val primaryDeviceDuration = primaryDevice.flaconParams?.let {
                (1 - it.volume / it.flaconType.volume) / primaryDeviceConsumeFrequency
            } ?: run {
                (1 - (primaryPeriod?.toFloat() ?: 0f) / primaryDeviceBuyPeriod) / primaryDeviceConsumeFrequency
            }

            val primaryConsumption = Consumption(
                frequency = primaryDeviceConsumeFrequency,
                durationInDays = primaryDeviceDuration,
                deviceId = primaryDeviceId,
                date = currentDate,
                isMeasured = true
            )

            val secondaryConsumption = secondaryDeviceId?.let { deviceId ->
                secondaryDeviceBuyPeriod?.let { period ->
                    secondaryPeriod?.let {
                        Consumption(
                            frequency = 1f / period,
                            deviceId = deviceId,
                            durationInDays = it.toFloat(),
                            date = currentDate,
                            isMeasured = true
                        )
                    }
                }
            }

            liquidRepository.saveConsumption(primaryConsumption)
            secondaryConsumption?.let {
                liquidRepository.saveConsumption(it)
            }

            dashboardRepository.saveInterests(startupParameters.interests)
            metaDataStore.saveIsStartupParametersSaved()
        }
        RootResult.Success(Unit)
    } catch (_: Exception) {
        RootResult.Failure(RequestError.GENERIC)
    }
}
