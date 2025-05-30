package ru.health.core.api

data class PropertyResult<D, out E : ResultError>(val data: D, val error: E? = null)
