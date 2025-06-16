package ru.health.featureachievement.api.presentation

interface HandleApproveEvent {

    fun onApproveEvent(
        approveTypeId: Int,
        approveEventType: ApproveEventType,
        approveValues: ApproveValues
    ) = Unit

}

