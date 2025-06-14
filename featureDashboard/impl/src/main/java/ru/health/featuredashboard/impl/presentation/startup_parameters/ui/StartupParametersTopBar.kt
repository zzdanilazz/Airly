package ru.health.featuredashboard.impl.presentation.startup_parameters.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.chrisbanes.haze.hazeEffect
import ru.health.core.api.domain.DeviceType
import ru.health.core.impl.presentation.ui.theme.AirlyTheme
import ru.health.core.impl.presentation.ui.theme.LightRed
import ru.health.core.impl.presentation.ui.theme.LocalHazeState
import ru.health.core.impl.presentation.ui.theme.RubikOneFamily
import ru.health.featuredashboard.impl.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun StartupParametersTopBar(
    modifier: Modifier = Modifier,
    deviceType: DeviceType? = DeviceType.POD,
    pagerState: PagerState = rememberPagerState { 3 },
) {
    MediumTopAppBar(
        modifier = modifier
            .hazeEffect(LocalHazeState.current)
            .padding(horizontal = 16.dp),
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = Color.Transparent
        ),
        navigationIcon = {
            val text = when (pagerState.currentPage) {
                0 -> stringResource(R.string.what_is_your_device)
                1 -> stringResource(R.string.how_much_do_you_consume)
                2 -> if (deviceType == DeviceType.POD) {
                    stringResource(R.string.what_is_the_liquid_rest)
                } else {
                    stringResource(R.string.what_is_your_interests)
                }
                3 -> stringResource(R.string.what_is_your_interests)
                else -> ""
            }
            Text(
                text = text,
                color = Color.White,
                fontSize = 24.sp,
                fontFamily = RubikOneFamily
            )
        },
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                for (page in 0 until pagerState.pageCount) {
                    val isActive = pagerState.currentPage >= page
                    val isNextPage = page == pagerState.currentPage + 1
                    val fillFraction = when {
                        isActive -> 1f
                        isNextPage -> pagerState.currentPageOffsetFraction / 0.5f
                        else -> 0f
                    }

                    Box(
                        modifier = Modifier
                            .height(12.dp)
                            .weight(1f)
                            .padding(horizontal = 4.dp)
                            .clip(RoundedCornerShape(50))
                            .background(Color.White.copy(alpha = 0.3f))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(fraction = fillFraction)
                                .background(LightRed, shape = RoundedCornerShape(50))
                        )
                    }
                }
            }
        }
    )
}

@Composable
@Preview
private fun StartupParametersTopBarPreview() {
    AirlyTheme {
        StartupParametersTopBar()
    }
}