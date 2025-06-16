package ru.health.featurestatistics.api.domain

import ru.health.core.api.ResultError
import ru.health.core.api.domain.result.RootResult
import ru.health.featureliquid.api.domain.model.Device

interface GetAllDevicesUseCase {

    suspend operator fun invoke(startDate: java.util.Date?, endDate: java.util.Date?): RootResult<List<Device>, ResultError>

}