package ru.health.airly.root.api

import ru.health.featurenotifications.api.presentation.ApproveComponent

sealed interface SlotChild {

    class Approve(val component: ApproveComponent) : SlotChild
}