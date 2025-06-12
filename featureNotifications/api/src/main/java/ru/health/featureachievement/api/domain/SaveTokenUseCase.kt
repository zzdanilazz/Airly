package ru.health.featurenotifications.api.domain

import ru.health.core.api.ResultError
import ru.health.core.api.domain.result.RootResult

interface SaveTokenUseCase {

    suspend operator fun invoke(token: String): RootResult<Unit, ResultError>

}
