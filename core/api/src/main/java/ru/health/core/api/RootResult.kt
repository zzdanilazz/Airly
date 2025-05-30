package ru.health.core.api.domain.result

import ru.health.core.api.ResultError


sealed class RootResult<D, out E : ResultError> private constructor() {

    val isSuccess: Boolean get() = this !is Failure
    val isError: Boolean get() = this is Failure

    val dataOrNull: D? get() = (this as? Success)?.data
    val errorOrNull: E? get() = (this as? Failure)?.error

    val dataOrThrow: D get() = (this as? Success)?.data ?: throw NoSuchElementException()
    val errorOrThrow: E get() = (this as? Failure)?.error ?: throw NoSuchElementException()

    fun dataOrDefault(default: D): D = dataOrNull ?: default

    inline fun onSuccess(action: (data: D) -> Unit): RootResult<D, E> =
        apply { dataOrNull?.let(action) }

    inline fun onFailure(action: (data: E) -> Unit) = apply { errorOrNull?.let(action) }

    data class Success<D, out E : ResultError>(val data: D) : RootResult<D, E>()
    data class Failure<D, out E : ResultError>(val error: E) : RootResult<D, E>()

}
