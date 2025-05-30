package ru.health.liquid.domain.usecase

import ru.health.core.api.ResultError
import ru.health.core.api.domain.result.RootResult
import ru.health.liquid.domain.model.VapeProduct

interface GetVapeProductUseCase {

    suspend operator fun invoke(isPod: Boolean? = null): RootResult<VapeProduct, ResultError>

}