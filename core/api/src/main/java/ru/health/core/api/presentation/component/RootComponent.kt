package ru.health.core.api.presentation.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

abstract class RootComponent<Config : Any, Child : Any>(
    componentContext: ComponentContext
) : DecomposeComponent<Config>(componentContext) {

    protected abstract val stack: Value<ChildStack<*, Child>>

    protected abstract fun child(config: Config, context: ComponentContext): Child

}
