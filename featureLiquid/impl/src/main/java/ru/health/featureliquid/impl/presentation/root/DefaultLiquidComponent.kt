package ru.health.featureliquid.impl.presentation.root

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.active
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.health.core.api.presentation.component.RootComponent
import ru.health.core.api.presentation.component.bottom_bar.BottomBarVisibility
import ru.health.featureachievement.api.presentation.ApproveEventType
import ru.health.featureachievement.api.presentation.ApproveParams
import ru.health.featureachievement.api.presentation.ApproveValues
import ru.health.featureliquid.api.domain.model.FlaconParams
import ru.health.featureliquid.api.presentation.detail.LiquidDetailComponent
import ru.health.featureliquid.api.presentation.root.LiquidChild
import ru.health.featureliquid.api.presentation.root.LiquidComponent

internal class DefaultLiquidComponent @AssistedInject internal constructor(
    private val liquidDetailFactory: LiquidDetailComponent.Factory,
    @Assisted componentContext: ComponentContext,
    @Assisted private val onInputLiquid: (flaconParams: FlaconParams) -> Unit,
    @Assisted private val onApprove: (approveParams: ApproveParams, approveTypeId: Int) -> Unit,
) : LiquidComponent, RootComponent<LiquidConfig, LiquidChild>(componentContext) {

    override val stack: Value<ChildStack<*, LiquidChild>> =
        childStack(
            source = navigation,
            initialConfiguration = LiquidConfig.LiquidDetail,
            serializer = LiquidConfig.serializer(),
            handleBackButton = true,
            childFactory = ::child,
        )

    override fun child(config: LiquidConfig, context: ComponentContext): LiquidChild = when (config) {
        LiquidConfig.LiquidDetail -> LiquidChild.LiquidDetail(liquidDetailComponent(context))
    }

    override fun onLiquidEdited(editedVolume: Float) {
        stack.active.instance.onLiquidEdited(editedVolume)
    }

    override fun onApproveEvent(
        approveTypeId: Int,
        approveEventType: ApproveEventType,
        approveValues: ApproveValues
    ) {
        stack.active.instance.onApproveEvent(approveTypeId, approveEventType, approveValues)
    }

    private fun liquidDetailComponent(context: ComponentContext): LiquidDetailComponent =
        liquidDetailFactory(
            componentContext = context,
            onInputLiquid = onInputLiquid,
            onApprove = onApprove
        )

    @Composable
    override fun BottomBarRender(modifier: Modifier, bottomBarVisibility: BottomBarVisibility) {
        val contentModifier = modifier.fillMaxSize()

        Children(
            stack = stack,
            animation = stackAnimation(fade()),
        ) {
            when (val child = it.instance) {
                is LiquidChild.LiquidDetail -> {
                    bottomBarVisibility.bottomBar.show()
                    child.component.Render(modifier = contentModifier)
                }
            }
        }
    }

    @AssistedFactory
    interface Factory : LiquidComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            onInputLiquid: (flaconParams: FlaconParams) -> Unit,
            onApprove: (approveParams: ApproveParams, approveTypeId: Int) -> Unit,
        ): DefaultLiquidComponent
    }
}
