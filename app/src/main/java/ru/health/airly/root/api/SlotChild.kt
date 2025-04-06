package ru.health.airly.root.api

import ru.health.featureapprove.presentation.ApproveComponent

sealed interface SlotChild {

    class Approve(val component: ApproveComponent) : SlotChild
}