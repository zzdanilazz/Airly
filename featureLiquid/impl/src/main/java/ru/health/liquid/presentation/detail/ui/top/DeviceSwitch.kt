package ru.health.liquid.presentation.detail.ui.top

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.health.core.impl.presentation.ui.theme.AirlyTheme
import ru.health.featureliquid.impl.R

@Composable
internal fun DeviceSwitch(
    modifier: Modifier = Modifier,
    isPodSelected: Boolean,
    onSwitch: (Boolean) -> Unit = {}
) {
    val shape = RoundedCornerShape(50)
    val iconTintActive = Color.White
    val thumbColor = iconTintActive.copy(alpha = 0.3f)

    val toggleOffset by animateDpAsState(
        if (isPodSelected) 0.dp else thumbWidth
    )

    Box(
        modifier = modifier
            .clip(shape)
            .border(
                width = 2.dp,
                color = thumbColor,
                shape = shape
            )
            .clickable { onSwitch(!isPodSelected) }
            .height(50.dp)
            .padding(5.dp)
            .width(thumbWidth * 2),
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .offset(x = toggleOffset)
                .fillMaxHeight()
                .fillMaxWidth(0.5f)
                .clip(shape)
                .background(thumbColor)
        )

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_pod),
                    tint = if (isPodSelected) iconTintActive else thumbColor,
                    contentDescription = null
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_disposable),
                    tint = if (!isPodSelected) iconTintActive else thumbColor,
                    contentDescription = null
                )
            }
        }
    }
}

private val thumbWidth = 70.dp

@Composable
@Preview
private fun DeviceSwitchPreview() {
    AirlyTheme {
        DeviceSwitch(isPodSelected = true)
    }
}