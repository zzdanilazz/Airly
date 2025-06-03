package ru.health.featureliquid.api.presentation.root

import ru.health.featureliquid.api.presentation.detail.LiquidDetailComponent
import ru.health.featureliquid.api.presentation.input.InputLiquidComponent

sealed interface LiquidChild {

    class LiquidDetail(val component: LiquidDetailComponent) : LiquidChild

    class InputLiquid(val component: InputLiquidComponent) : LiquidChild
}