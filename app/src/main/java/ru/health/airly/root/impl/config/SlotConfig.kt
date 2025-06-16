package ru.health.airly.root.impl.config

import kotlinx.serialization.Serializable
import ru.health.featureachievement.api.presentation.ApproveParams

@Serializable
sealed interface SlotConfig {

    @Serializable
    data class Approve(val params: ApproveParams, val approveTypeId: Int) : SlotConfig
}