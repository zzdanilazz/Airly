package ru.health.featureliquid.impl.presentation.detail.ui.bottle

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import ru.health.core.impl.presentation.ui.gradient.GradientBox
import ru.health.core.impl.presentation.ui.theme.AirlyTheme
import ru.health.core.impl.presentation.ui.theme.LightRed
import ru.health.core.impl.presentation.ui.theme.ModerateRed
import ru.health.core.impl.presentation.ui.theme.PaleRed
import ru.health.core.impl.presentation.ui.theme.RubikOneFamily
import ru.health.core.impl.presentation.ui.theme.VeryDarkGray
import ru.health.featureliquid.api.domain.model.BottleType
import ru.health.featureliquid.api.domain.model.VapeProduct
import ru.health.featureliquid.impl.R
import ru.health.featureliquid.impl.presentation.detail.ui.liquidPreview
import kotlin.math.sqrt

@Composable
internal fun Bottle(
    modifier: Modifier = Modifier,
    liquid: VapeProduct.Liquid
) {
    Column(modifier = modifier) {
        Cap(
            modifier = Modifier.zIndex(1f),
            bottleType = liquid.bottleType
        )
        Flacon(
            modifier = Modifier.offset(y = (-30).dp),
            bottleType = liquid.bottleType,
            currentVolume = liquid.currentVolume
        )
    }
}

@Composable
private fun Cap(
    modifier: Modifier = Modifier,
    bottleType: BottleType
) {
    Column(modifier = modifier) {
        Image(
            modifier = Modifier.size(bottleType.upperCapSize),
            painter = painterResource(R.drawable.illustration_cap),
            contentScale = ContentScale.FillBounds,
            contentDescription = null
        )
        val brush = Brush.verticalGradient(
            listOf(PaleRed, LightRed)
        )
        Box(
            modifier = Modifier
                .offset(y = (-5).dp)
                .size(bottleType.bottomCapSize)
                .background(brush, RoundedCornerShape(5.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.ml, bottleType.volume),
                fontFamily = RubikOneFamily,
                fontSize = 24.sp,
                color = ModerateRed
            )
        }
        Spacer(
            modifier = Modifier
                .width(bottleType.bottomCapSize.width)
                .height(16.dp)
                .background(brush, RoundedCornerShape(5.dp))
        )
    }
}

@Composable
private fun Flacon(
    modifier: Modifier = Modifier,
    bottleType: BottleType,
    currentVolume: Int
) {
    val shape = RoundedCornerShape(26.dp)
    val liquidFraction = currentVolume / bottleType.volume.toFloat()
    val totalSize = bottleType.flaconSize
    val flaconColor = if (isSystemInDarkTheme()) Color.White else Color.Black

    Column(
        modifier = modifier
            .size(totalSize)
            .clip(shape)
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f - liquidFraction, fill = true)
                .background(flaconColor.copy(alpha = 0.25f))
        )
        Liquid(
            modifier = Modifier
                .fillMaxWidth()
                .weight(liquidFraction, fill = true)
                .defaultMinSize(minHeight = 32.dp),
            currentVolume = currentVolume
        )
    }
}

@Composable
private fun Liquid(
    modifier: Modifier = Modifier,
    currentVolume: Int,
) {
    val brush = Brush.verticalGradient(
        listOf(VeryDarkGray, Color.Black)
    )
    Box(
        modifier = modifier.background(brush),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.ml, currentVolume),
            fontFamily = RubikOneFamily,
            fontSize = 24.sp,
            color = Color.White
        )
    }
}

private val BottleType.flaconSize: DpSize
    get() = when (this) {
        BottleType.SMALL -> DpSize(width = 138.dp, height = 265.dp)
        BottleType.TALL -> DpSize(width = 138.dp, height = 530.dp)
        BottleType.LARGE -> DpSize(width = (138 * sqrt(2f)).dp, height = 530.dp)
    }

private val BottleType.upperCapSize: DpSize
    get() = DpSize(width = this.flaconSize.width, height = 68.dp)

private val BottleType.bottomCapSize: DpSize
    get() = DpSize(width = this.flaconSize.width, height = 106.dp)

@PreviewLightDark
@Composable
private fun SmallBottlePreview() {
    AirlyTheme {
        GradientBox {
            Bottle(
                modifier = Modifier.align(Alignment.Center),
                liquid = liquidPreview
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun TallBottlePreview() {
    AirlyTheme {
        GradientBox {
            Bottle(
                modifier = Modifier.align(Alignment.Center),
                liquid = liquidPreview.copy(
                    bottleType = BottleType.TALL
                )
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun LargeBottlePreview() {
    AirlyTheme {
        GradientBox {
            Bottle(
                modifier = Modifier.align(Alignment.Center),
                liquid = liquidPreview.copy(
                    bottleType = BottleType.LARGE
                )
            )
        }
    }
}