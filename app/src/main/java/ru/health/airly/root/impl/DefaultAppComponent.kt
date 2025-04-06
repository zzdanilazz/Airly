package ru.health.airly.root.impl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.experimental.stack.ChildStack
import com.arkivanov.decompose.extensions.compose.experimental.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.experimental.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.value.Value
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.health.airly.root.api.AppComponent
import ru.health.airly.root.api.Child
import ru.health.airly.root.api.SlotChild
import ru.health.airly.root.impl.config.Config
import ru.health.airly.root.impl.config.SlotConfig
import ru.health.airly.root.impl.ui.StackContent
import ru.health.airly.root.impl.ui.SlotContent
import ru.health.core.presentation.component.SlotRootComponent
import ru.health.featureapprove.presentation.ApproveComponent
import ru.health.featuredashboard.presentation.DashboardComponent

class DefaultAppComponent @AssistedInject internal constructor(
    private val dashboardFactory: DashboardComponent.Factory,
    private val approveFactory: ApproveComponent.Factory,
    @Assisted componentContext: ComponentContext,
) : SlotRootComponent<Config, Child, SlotConfig, SlotChild>(componentContext), AppComponent {

    override val stack: Value<ChildStack<*, Child>> =
        childStack(
            source = navigation,
            initialConfiguration = Config.Dashboard,
            serializer = Config.serializer(),
            handleBackButton = true,
            childFactory = ::child,
        )

    override val slot: Value<ChildSlot<*, SlotChild>> =
        childSlot(
            source = slotNavigation,
            handleBackButton = true,
            serializer = SlotConfig.serializer(),
            childFactory = ::slotChild,
        )

    override fun child(config: Config, context: ComponentContext): Child = when(config) {
        is Config.Dashboard -> Child.Dashboard(dashboard(context))
    }

    override fun slotChild(slotConfig: SlotConfig, context: ComponentContext): SlotChild =
        when(slotConfig) {
            is SlotConfig.Approve -> SlotChild.Approve(approve(context))
        }

    private fun dashboard(context: ComponentContext) = dashboardFactory(
        componentContext = context,
        onBack = { navigation.pop() }
    )

    private fun approve(context: ComponentContext) = approveFactory(
        componentContext = context,
        onDismiss = { slotNavigation.dismiss() }
    )

    @OptIn(ExperimentalDecomposeApi::class)
    @Composable
    override fun Render(modifier: Modifier) {
        ChildStack(
            modifier = modifier,
            stack = stack,
            animation = stackAnimation(animator = fade()),
        ) {
            StackContent(it.instance)

            val slot by slot.subscribeAsState()
            slot.child?.instance?.let {
                SlotContent(it)
            }
        }
    }

    @AssistedFactory
    interface Factory : AppComponent.Factory {
        override fun invoke(componentContext: ComponentContext): DefaultAppComponent
    }
}