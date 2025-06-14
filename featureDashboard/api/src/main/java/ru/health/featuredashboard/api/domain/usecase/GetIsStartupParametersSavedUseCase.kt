package ru.health.featuredashboard.api.domain.usecase

import ru.health.core.api.ResultError
import ru.health.core.api.domain.result.RootResult

interface GetIsStartupParametersSavedUseCase {

    suspend operator fun invoke(): RootResult<Boolean, ResultError>

}