package ru.health.featureliquid.api.presentation.root

import ru.health.featureachievement.api.presentation.HandleApproveEvent
import ru.health.featureliquid.api.presentation.detail.LiquidDetailComponent
import ru.health.featureliquid.api.presentation.input.EditLiquidCallback

sealed interface LiquidChild: EditLiquidCallback, HandleApproveEvent {

    class LiquidDetail(val component: LiquidDetailComponent) : LiquidChild,
        EditLiquidCallback by component, HandleApproveEvent by component
}