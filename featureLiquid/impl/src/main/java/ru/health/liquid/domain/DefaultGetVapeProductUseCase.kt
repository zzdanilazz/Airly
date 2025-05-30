package ru.health.liquid.domain

import ru.health.core.api.RequestError
import ru.health.core.api.ResultError
import ru.health.core.api.domain.result.RootResult
import ru.health.liquid.domain.model.VapeProduct
import ru.health.liquid.domain.usecase.GetVapeProductUseCase
import ru.health.liquid.presentation.detail.ui.liquidPreview
import javax.inject.Inject

class DefaultGetVapeProductUseCase @Inject constructor(

) : GetVapeProductUseCase {

    override suspend fun invoke(isPod: Boolean?): RootResult<VapeProduct, ResultError> = try {
        //todo для теста
        val vapeProduct = isPod?.let {
            if (isPod) {
                liquidPreview
            } else {
                VapeProduct.Disposable(
                    id = 1,
                    totalPuffs = 10_000,
                    currentPuffs = 503
                )
            }
        } ?: liquidPreview

        vapeProduct?.let { RootResult.Success(it) } ?: RootResult.Failure(RequestError.GENERIC)
    } catch (_: Exception) {
        RootResult.Failure(RequestError.GENERIC)
    }
}
