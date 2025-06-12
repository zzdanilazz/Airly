package ru.health.core.impl.domain

import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive
import java.time.Instant
import java.util.Date
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

fun getDurationFlow(date: Date) : Flow<Duration> = flow {
    while (currentCoroutineContext().isActive) {
        val currentTimeMillis = Instant.now().toEpochMilli()
        emit((currentTimeMillis - date.time).milliseconds)
        delay(1_000)
    }
}