package ru.health.featuredashboard.impl.domain

import ru.health.core.api.RequestError
import ru.health.core.api.ResultError
import ru.health.core.api.data.MetaDataStore
import ru.health.core.api.domain.result.RootResult
import ru.health.featuredashboard.api.domain.usecase.GetIsStartupParametersSavedUseCase
import javax.inject.Inject
import kotlin.time.ExperimentalTime

class DefaultGetIsStartupParametersSavedUseCase @Inject constructor(
    private val metaDataStore: MetaDataStore
) : GetIsStartupParametersSavedUseCase {

    @OptIn(ExperimentalTime::class)
    override suspend fun invoke(): RootResult<Boolean, ResultError> = try {
        RootResult.Success(metaDataStore.getIsStartupParametersSaved())
    } catch (_: Exception) {
        RootResult.Failure(RequestError.GENERIC)
    }
}
