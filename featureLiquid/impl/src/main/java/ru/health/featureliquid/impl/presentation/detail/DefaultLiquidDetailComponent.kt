package ru.health.featureliquid.impl.presentation.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.health.core.api.domain.DeviceType
import ru.health.core.impl.presentation.EventEffect
import ru.health.featureachievement.api.presentation.ApproveEventType
import ru.health.featureachievement.api.presentation.ApproveParams
import ru.health.featureachievement.api.presentation.ApproveValues
import ru.health.featureliquid.api.domain.model.FlaconParams
import ru.health.featureliquid.api.presentation.detail.LiquidDetailComponent
import ru.health.featureliquid.impl.R
import ru.health.featureliquid.impl.presentation.detail.ui.LiquidDetailContent
import ru.health.core.impl.R as CoreR

internal class DefaultLiquidDetailComponent @AssistedInject internal constructor(
    @Assisted componentContext: ComponentContext,
    @Assisted private val onInputLiquid: (flaconParams: FlaconParams) -> Unit,
    @Assisted private val onApprove: (approveParams: ApproveParams, approveTypeId: Int) -> Unit,
    private val liquidDetailViewModel: LiquidDetailViewModel.Factory,
) : LiquidDetailComponent, ComponentContext by componentContext {

    private val viewModel = instanceKeeper.getOrCreate { liquidDetailViewModel() }

    override fun onLiquidEdited(editedVolume: Float) {
        viewModel.onAction(LiquidDetailAction.OnLiquidEdited(editedVolume))
    }

    override fun onApproveEvent(
        approveTypeId: Int,
        approveEventType: ApproveEventType,
        approveValues: ApproveValues
    ) {
        if (approveEventType == ApproveEventType.APPROVE_BUTTON_CLICK) {
            when (approveTypeId) {
                LiquidDetailApproveType.ADD_CONSUMPTION.id -> onAddConsumptionApproved(approveValues)
                LiquidDetailApproveType.ADD_DEVICE.id -> onAddDeviceApproved()
                LiquidDetailApproveType.ADD_FLACON.id -> onAddFlacon(approveValues)
                LiquidDetailApproveType.ADD_VAPORIZER.id -> onAddVaporizer(approveValues)
                LiquidDetailApproveType.ADD_DISPOSABLE.id -> onAddDisposable(approveValues)
            }
        }
    }

    @Composable
    override fun Render(modifier: Modifier) {
        EventEffect(viewModel.navEvent) { event ->
            when (event) {
                LiquidDetailNavEvent.AddConsumptionApprove -> onAddConsumptionApprove()
                LiquidDetailNavEvent.AddVaporizerApprove -> onAddVaporizerApprove()
                is LiquidDetailNavEvent.EditLiquidLevel -> onInputLiquid(event.flaconParams)
                is LiquidDetailNavEvent.AddPrimaryDevice -> onAddPrimaryDeviceApprove(event.deviceType)
            }
        }

        LiquidDetailContent(
            modifier = modifier,
            viewModel = viewModel
        )
    }

    private fun onAddConsumptionApprove() = onApprove(
        ApproveParams.Builder()
            .setTitle(R.string.add_consumption_title)
            .setApproveButtonText(CoreR.string.add)
            .setDeclineButtonText(CoreR.string.cancel)
            .setFieldTextPlaceholder("2")
            .setFieldTrailingText("мин")
            .build(),
        LiquidDetailApproveType.ADD_CONSUMPTION.id
    )

    private fun onAddConsumptionApproved(approveValues: ApproveValues) {
        approveValues.fieldText?.let {
            val vapeDurationMinutes = it.toInt()
            val vapeDurationDaysFloat = vapeDurationMinutes.toFloat() / 1440
            viewModel.onAction(LiquidDetailAction.AddConsumption(vapeDurationDaysFloat))
        }
    }

    private fun onAddVaporizerApprove() = onApprove(
        ApproveParams.Builder()
            .setTitle(R.string.add_vaporizer_title)
            .setFieldTextPlaceholder("300")
            .setFieldTrailingText("₽")
            .setApproveButtonText(CoreR.string.add)
            .setDeclineButtonText(CoreR.string.cancel)
            .build(),
        LiquidDetailApproveType.ADD_VAPORIZER.id
    )

    private fun onAddDeviceApproved() {
        CoroutineScope(viewModel.coroutineContext).launch {
            delay(100)
            when (viewModel.state.value.primaryDevice?.deviceType) {
                DeviceType.POD -> fillFlacon()
                DeviceType.DISPOSABLE -> fillDisposable()
                else -> {}
            }
        }
    }

    private fun onAddFlacon(approveValues: ApproveValues) {
        val fieldText = approveValues.fieldText
        val selectedSwitchIndex = approveValues.selectedSwitchIndex
        if (fieldText != null && selectedSwitchIndex != null) {
            viewModel.onAction(LiquidDetailAction.AddFlacon(fieldText, selectedSwitchIndex))
        }
    }

    private fun onAddVaporizer(approveValues: ApproveValues) {
        val price = approveValues.fieldText?.toIntOrNull()
        price?.let {
            viewModel.onAction(LiquidDetailAction.AddVaporizer(it))
        }
    }

    private fun onAddDisposable(approveValues: ApproveValues) {
        val price = approveValues.fieldText?.toIntOrNull()
        price?.let {
            viewModel.onAction(LiquidDetailAction.AddDisposable(it))
        }
    }

    private fun fillFlacon() = onApprove(
        ApproveParams.Builder()
            .setTitle(R.string.fill_flacon_title)
            .setApproveButtonText(CoreR.string.add)
            .setDeclineButtonText(CoreR.string.cancel)
            .setSwitchIconResList(
                listOf(
                    CoreR.drawable.ic_small_flacon,
                    CoreR.drawable.ic_tall_flacon,
                    CoreR.drawable.ic_large_flacon
                )
            )
            .setFieldTextPlaceholder("500")
            .setFieldTrailingText("₽")
            .build(),
        LiquidDetailApproveType.ADD_FLACON.id
    )

    private fun fillDisposable() = onApprove(
        ApproveParams.Builder()
            .setTitle(R.string.fill_disposable_title)
            .setApproveButtonText(CoreR.string.add)
            .setDeclineButtonText(CoreR.string.cancel)
            .setFieldTextPlaceholder("2000")
            .setFieldTrailingText("₽")
            .build(),
        LiquidDetailApproveType.ADD_DISPOSABLE.id
    )

    private fun onAddPrimaryDeviceApprove(deviceType: DeviceType) = onApprove(
        ApproveParams.Builder()
            .setTitle(
                if (deviceType == DeviceType.POD) {
                    R.string.add_flacon_title
                } else R.string.add_disposable_title
            )
            .setDescription(
                if (deviceType == DeviceType.POD) {
                    R.string.add_flacon_desc
                } else R.string.add_disposable_desc
            )
            .setApproveButtonText(CoreR.string.proceed)
            .setDeclineButtonText(CoreR.string.cancel)
            .build(),
        LiquidDetailApproveType.ADD_DEVICE.id
    )

    @AssistedFactory
    interface Factory : LiquidDetailComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            onInputLiquid: (flaconParams: FlaconParams) -> Unit,
            onApprove: (approveParams: ApproveParams, approveTypeId: Int) -> Unit,
        ): DefaultLiquidDetailComponent
    }

    private enum class LiquidDetailApproveType(val id: Int) {
        ADD_CONSUMPTION(0), ADD_DEVICE(1), ADD_FLACON(2), ADD_VAPORIZER(3), ADD_DISPOSABLE(4)
    }
}
