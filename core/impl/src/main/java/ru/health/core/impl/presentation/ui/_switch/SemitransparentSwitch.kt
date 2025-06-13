package ru.health.core.impl.presentation.ui._switch

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun <T> SemitransparentSwitch(
    modifier: Modifier = Modifier,
    width: Dp = SwitchWidth,
    height: Dp = SwitchHeight,
    items: List<Pair<T, Int>>,
    selectedIndex: Int,
    onSwitch: (item: T) -> Unit = {}
) {
    val shape = RoundedCornerShape(50)
    val iconTintActive = Color.White
    val thumbColor = iconTintActive.copy(alpha = 0.3f)

    val thumbWidth = getThumbWidth(items.size, width)
    val toggleOffset by animateDpAsState(
        thumbWidth * selectedIndex
    )

    Box(
        modifier = modifier
            .height(height)
            .clip(shape)
            .border(
                width = 2.dp,
                color = thumbColor,
                shape = shape
            )
            .clickable {
                onSwitch(
                    items[(selectedIndex + 1) % items.size].first
                )
            }
            .padding(5.dp)
            .width(width),
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .offset(x = toggleOffset)
                .fillMaxHeight()
                .fillMaxWidth(1f / items.size)
                .clip(shape)
                .background(thumbColor)
        )

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { (item, iconResId) ->
                val isCurrentItemSelected = item == items[selectedIndex].first

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .scale(animateScale(isCurrentItemSelected)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(iconResId),
                        tint = if (isCurrentItemSelected) iconTintActive else thumbColor,
                        contentDescription = null
                    )
                }
            }
        }
    }
}

private val SwitchHeight = 50.dp
private val SwitchWidth = 135.dp

private fun getThumbWidth(itemsCount: Int, totalWidth: Dp) = totalWidth / itemsCount

@Composable
private fun animateScale(isSelected: Boolean): Float {
    val scale by animateFloatAsState(
        targetValue = if (isSelected) 1f else .95f,
        visibilityThreshold = .000001f,
        animationSpec = spring(
            stiffness = Spring.StiffnessLow,
            dampingRatio = Spring.DampingRatioMediumBouncy,
        ),
        label = "scale"
    )
    return scale
}