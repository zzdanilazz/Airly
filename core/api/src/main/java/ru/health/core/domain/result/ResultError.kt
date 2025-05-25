package ru.health.core.domain.result

interface ResultError

enum class RequestError: ResultError {
    GENERIC, AUTH, SERVER
}

sealed interface ResponseError: ResultError {

    data class BadRequest(val localizedError: String?): ResponseError

}
