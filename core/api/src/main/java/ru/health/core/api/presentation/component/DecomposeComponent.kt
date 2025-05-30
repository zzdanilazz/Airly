package ru.health.core.api.presentation.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation

abstract class DecomposeComponent<Config : Any>(
    componentContext: ComponentContext
) : ComponentContext by componentContext {

    protected val navigation = StackNavigation<Config>()

}