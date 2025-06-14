package ru.health.featureliquid.api.presentation.root

import ru.health.featureliquid.api.presentation.detail.LiquidDetailComponent

sealed interface LiquidChild {

    class LiquidDetail(val component: LiquidDetailComponent) : LiquidChild
}