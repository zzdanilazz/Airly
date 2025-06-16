package ru.health.featureliquid.impl.domain

import kotlinx.coroutines.flow.firstOrNull
import ru.health.core.api.RequestError
import ru.health.core.api.ResultError
import ru.health.core.api.domain.result.RootResult
import ru.health.featureliquid.api.domain.LiquidRepository
import ru.health.featureliquid.api.domain.usecase.EditLiquidUseCase
import javax.inject.Inject

class DefaultEditLiquidUseCase @Inject constructor(
    private val liquidRepository: LiquidRepository
) : EditLiquidUseCase {

    override suspend fun invoke(editedVolume: Float): RootResult<Unit, ResultError> = try {
        val editedDevice = liquidRepository.getLatestDeviceFlow().firstOrNull()?.let {
            it.copy(flaconParams = it.flaconParams?.copy(volume = editedVolume))
        }

        editedDevice?.let {
            RootResult.Success(liquidRepository.updateDevice(it))
        } ?: RootResult.Failure(RequestError.GENERIC)
    } catch (_: Exception) {
        RootResult.Failure(RequestError.GENERIC)
    }
}
