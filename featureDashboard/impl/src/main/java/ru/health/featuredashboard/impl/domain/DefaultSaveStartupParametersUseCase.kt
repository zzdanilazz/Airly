package ru.health.featuredashboard.impl.domain

import android.util.Log
import ru.health.core.api.RequestError
import ru.health.core.api.ResultError
import ru.health.core.api.data.MetaDataStore
import ru.health.core.api.domain.result.RootResult
import ru.health.featuredashboard.api.domain.DashboardRepository
import ru.health.featuredashboard.api.domain.model.StartupParameters
import ru.health.featuredashboard.api.domain.usecase.SaveStartupParametersUseCase
import ru.health.featureliquid.api.domain.LiquidRepository
import ru.health.featureliquid.api.domain.model.ConsumptionFrequency
import javax.inject.Inject

class DefaultSaveStartupParametersUseCase @Inject constructor(
    private val dashboardRepository: DashboardRepository,
    private val liquidRepository: LiquidRepository,
    private val metaDataStore: MetaDataStore,
) : SaveStartupParametersUseCase {

    override suspend fun invoke(startupParameters: StartupParameters): RootResult<Unit, ResultError> = try {
        with (startupParameters) {
            val primaryDeviceId = liquidRepository.saveDevice(primaryDevice)
            val secondaryDeviceId = secondaryDevice?.let {
                liquidRepository.saveDevice(it)
            }
            Log.e("TAG", "primaryDeviceId: $primaryDeviceId")
            Log.e("TAG", "secondaryDeviceId: $secondaryDeviceId")

            val primaryConsumptionFrequency = ConsumptionFrequency(
                value = 1f / primaryDeviceBuyPeriod,
                deviceId = primaryDeviceId
            )

            val secondaryConsumptionFrequency = secondaryDeviceId?.let { deviceId ->
                secondaryDeviceBuyPeriod?.let { period ->
                    ConsumptionFrequency(
                        value = 1f / period,
                        deviceId = deviceId
                    )
                }
            }

            liquidRepository.saveConsumptionFrequency(primaryConsumptionFrequency)
            secondaryConsumptionFrequency?.let {
                liquidRepository.saveConsumptionFrequency(it)
            }

            dashboardRepository.saveInterests(startupParameters.interests)
            metaDataStore.saveIsStartupParametersSaved()
        }
        RootResult.Success(Unit)
    } catch (_: Exception) {
        RootResult.Failure(RequestError.GENERIC)
    }
}
