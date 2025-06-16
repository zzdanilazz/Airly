package ru.health.featuredashboard.api.domain.usecase

import ru.health.core.api.ResultError
import ru.health.core.api.domain.result.RootResult

interface SaveIsProgressSharedUseCase {

    suspend operator fun invoke(): RootResult<Unit, ResultError>

}