package ru.health.featuredashboard.presentation.ui.banner

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yandex.mobile.ads.common.AdRequestError
import com.yandex.mobile.ads.feed.FeedAd
import com.yandex.mobile.ads.feed.FeedAdAdapter
import com.yandex.mobile.ads.feed.FeedAdAppearance
import com.yandex.mobile.ads.feed.FeedAdLoadListener
import com.yandex.mobile.ads.feed.FeedAdRequestConfiguration
import ru.health.featuredashboard.impl.R
import kotlin.math.roundToInt

@Composable
internal fun BannerFeed(
    modifier: Modifier = Modifier,
    maxPrice: Float
) {
    val windowInfo = LocalWindowInfo.current
    val density = LocalDensity.current
    val context = LocalContext.current

    val screenWidthDp = with (density) { windowInfo.containerSize.width.toDp() }.value
    val cardWidthDp = screenWidthDp.roundToInt() - 2 * 16
    val cardCornerRadiusDp = 16.0

    val feedAdAppearance = FeedAdAppearance.Builder(cardWidthDp)
        .setCardCornerRadius(cardCornerRadiusDp)
        .build()

    val adUnitId = stringResource(R.string.dashboard_banner_unit_id)
    val parameters = mapOf(
        "цена" to maxPrice.toString()
    )
    val feedAdRequestConfiguration = FeedAdRequestConfiguration.Builder(adUnitId)
        .setParameters(parameters)
        .build()

    val feedAdLoadListener = object : FeedAdLoadListener {

        override fun onAdLoaded() {}
        override fun onAdFailedToLoad(error: AdRequestError) {}
    }

    val feedAd = FeedAd.Builder(context, feedAdRequestConfiguration, feedAdAppearance).build()
    feedAd.loadListener = feedAdLoadListener

    val feedAdAdapter = FeedAdAdapter(feedAd)

    LaunchedEffect(Unit) {
        feedAd.preloadAd()
    }

    Column {
        Spacer(modifier = Modifier.height(32.dp))
        RecyclerView(
            modifier = modifier.fillMaxWidth(),
            adapter = feedAdAdapter
        )
    }
}