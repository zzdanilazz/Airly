package ru.health.liquid.domain.usecase

import ru.health.core.domain.result.ResultError
import ru.health.core.domain.result.RootResult
import ru.health.liquid.domain.model.VapeProduct

interface GetVapeProductUseCase {

    suspend operator fun invoke(isPod: Boolean? = null): RootResult<VapeProduct, ResultError>

}