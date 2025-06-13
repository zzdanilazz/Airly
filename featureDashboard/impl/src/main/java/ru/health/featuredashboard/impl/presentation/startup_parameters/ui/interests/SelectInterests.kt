package ru.health.featuredashboard.impl.presentation.startup_parameters.ui.interests

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.health.core.impl.presentation.ui.button.PrimaryButton
import ru.health.core.impl.presentation.ui.gradient.GradientBox
import ru.health.core.impl.presentation.ui.theme.AirlyTheme
import ru.health.core.impl.presentation.ui.theme.LightRed
import ru.health.featuredashboard.impl.R
import ru.health.featuredashboard.impl.presentation.startup_parameters.model.Interest
import ru.health.featuredashboard.impl.presentation.startup_parameters.ui.startupParametersUiStatePreview
import kotlin.random.Random

@Composable
internal fun SelectInterests(
    modifier: Modifier = Modifier,
    interests: List<Interest>,
    onSelect: (interestIndex: Int) -> Unit = {},
    onProceed: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .navigationBarsPadding()
    ) {
        FlowRow(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            interests.forEachIndexed { index, interest ->
                FilterChip(
                    selected = interest.selected,
                    onClick = { onSelect(index) },
                    shape = RoundedCornerShape(50),
                    colors = FilterChipDefaults.filterChipColors().copy(
                        containerColor = Color.White.copy(0.3f),
                        selectedContainerColor = Color.White,
                        labelColor = Color.White,
                        selectedLabelColor = LightRed
                    ),
                    label = {
                        Text(
                            modifier = Modifier.padding(10.dp),
                            text = interest.name,
                            fontSize = 16.sp
                        )
                    },
                    border = null
                )
            }
        }
        PrimaryButton(
            modifier = Modifier.align(Alignment.BottomCenter),
            isEnabled = interests.any { it.selected },
            buttonText = stringResource(R.string.proceed),
            onButtonClick = onProceed
        )
    }
}

@PreviewLightDark
@Composable
private fun SelectInterestsPreview() {
    AirlyTheme {
        GradientBox {
            SelectInterests(interests = startupParametersUiStatePreview.interests)
        }
    }
}

@PreviewLightDark
@Composable
private fun SelectInterestsSelectedPreview() {
    AirlyTheme {
        GradientBox {
            SelectInterests(interests = startupParametersUiStatePreview.interests.map {
                it.copy(selected = Random.nextBoolean())
            })
        }
    }
}