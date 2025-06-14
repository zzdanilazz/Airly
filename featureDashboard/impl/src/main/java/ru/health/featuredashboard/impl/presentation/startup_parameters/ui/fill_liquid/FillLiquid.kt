package ru.health.featuredashboard.impl.presentation.startup_parameters.ui.fill_liquid

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.health.core.api.domain.BottleType
import ru.health.core.impl.presentation.iconResId
import ru.health.core.impl.presentation.ui.button.PrimaryButton
import ru.health.core.impl.presentation.ui.card.GlassmorphismCard
import ru.health.core.impl.presentation.ui.card.GlassmorphismCardShape
import ru.health.core.impl.presentation.ui.gradient.GradientBox
import ru.health.core.impl.presentation.ui.theme.AirlyTheme
import ru.health.core.impl.presentation.ui.theme.LightRed
import ru.health.core.impl.presentation.ui.theme.RubikOneFamily
import ru.health.featuredashboard.impl.R
import ru.health.featuredashboard.impl.presentation.startup_parameters.StartupParametersAction
import ru.health.featuredashboard.impl.presentation.startup_parameters.StartupParametersUiState
import ru.health.featuredashboard.impl.presentation.startup_parameters.ui.startupParametersUiStatePreview
import ru.health.core.impl.R as CoreR

@Composable
internal fun FillLiquid(
    modifier: Modifier = Modifier,
    state: StartupParametersUiState,
    onAction: (action: StartupParametersAction) -> Unit = {},
    onProceed: () -> Unit = {}
) {
    val selectedBottleType = state.bottleType

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .navigationBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(CoreR.string.bottle_volume),
                fontSize = 24.sp,
                fontFamily = RubikOneFamily,
                color = Color.White
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                BottleType.entries.forEach {
                    val scale by animateFloatAsState(if (selectedBottleType == it) 1.1f else 1f)
                    val containerColor by animateColorAsState(
                        if (selectedBottleType == it) Color.White else Color.Transparent
                    )
                    val contentColor by animateColorAsState(
                        if (selectedBottleType == it) LightRed else Color.White
                    )

                    GlassmorphismCard(
                        modifier = Modifier
                            .weight(1f)
                            .background(containerColor, GlassmorphismCardShape)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onAction(StartupParametersAction.SelectBottleType(it))
                                }
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                modifier = Modifier.size(40.dp * scale),
                                painter = painterResource(it.iconResId),
                                tint = contentColor,
                                contentDescription = null,
                            )
                        }
                    }
                }
            }
            val device = state.device
            val currentVolume = device?.currentVolume
            if (device != null && currentVolume != null) {
                val currentVolumeMl = stringResource(
                    CoreR.string.ml,
                    currentVolume
                )
                Text(
                    text = stringResource(
                        R.string.current_volume,
                        currentVolumeMl
                    ),
                    fontSize = 24.sp,
                    fontFamily = RubikOneFamily,
                    color = Color.White
                )
                PrimaryButton(
                    isEnabled = selectedBottleType != null,
                    buttonText = stringResource(R.string.edit_current_volume),
                    iconResId = CoreR.drawable.ic_level,
                    containerColor = Color.White,
                    contentColor = LightRed,
                    onButtonClick = {
                        onAction(StartupParametersAction.OnLiquidLevelClick)
                    }
                )
            }
        }
        PrimaryButton(
            modifier = Modifier.align(Alignment.BottomCenter),
            isEnabled = selectedBottleType != null,
            buttonText = stringResource(R.string.proceed),
            onButtonClick = {
                selectedBottleType?.let { onProceed() }
            }
        )
    }
}

@PreviewLightDark
@Composable
private fun FillLiquidPreview() {
    AirlyTheme {
        GradientBox {
            FillLiquid(state = startupParametersUiStatePreview)
        }
    }
}