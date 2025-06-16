package ru.health.featuredashboard.impl.domain

import ru.health.core.api.RequestError
import ru.health.core.api.ResultError
import ru.health.core.api.data.MetaDataStore
import ru.health.core.api.domain.result.RootResult
import ru.health.featuredashboard.api.domain.usecase.SaveIsProgressSharedUseCase
import javax.inject.Inject

class DefaultSaveIsProgressSharedUseCase @Inject constructor(
    private val metaDataStore: MetaDataStore,
) : SaveIsProgressSharedUseCase {

    override suspend fun invoke(): RootResult<Unit, ResultError> = try {
        metaDataStore.saveIsProgressShared()
        RootResult.Success(Unit)
    } catch (_: Exception) {
        RootResult.Failure(RequestError.GENERIC)
    }
}
