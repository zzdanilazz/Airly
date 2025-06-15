package ru.health.core.impl.domain

import kotlin.time.Duration

val Duration.inDaysFloat: Float
    get() = this.inWholeSeconds / SECONDS_IN_DAY

private const val SECONDS_IN_DAY = 86_400f