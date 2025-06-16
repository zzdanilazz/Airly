package ru.health.featureliquid.api.domain.usecase

import ru.health.core.api.ResultError
import ru.health.core.api.domain.result.RootResult

interface EditLiquidUseCase {

    suspend operator fun invoke(editedVolume: Float): RootResult<Unit, ResultError>

}