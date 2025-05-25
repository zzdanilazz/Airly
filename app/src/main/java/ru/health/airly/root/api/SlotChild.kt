package ru.health.airly.root.api

import ru.health.featurenotifications.presentation.ApproveComponent

sealed interface SlotChild {

    class Approve(val component: ApproveComponent) : SlotChild
}