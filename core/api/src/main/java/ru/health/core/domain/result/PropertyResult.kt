package ru.health.core.domain.result

data class PropertyResult<D, out E : ResultError>(val data: D, val error: E? = null)
