package ru.health.core.api

interface Factory<in T, out P> {
    fun create(item: T): P
}
