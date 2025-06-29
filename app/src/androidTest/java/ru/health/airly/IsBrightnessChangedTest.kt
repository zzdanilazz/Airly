package ru.health.airly

import android.app.Activity
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import com.atiurin.ultron.core.compose.createDefaultUltronComposeRule
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class IsBrightnessChangedTest {
    @get:Rule
    val composeRule = createDefaultUltronComposeRule()
    @Test
    fun shouldUpdateBrightnessToMax() {
        lateinit var activity: Activity
        composeRule.setContent {
            val context = LocalContext.current
            activity = context as Activity
            DisposableEffect(Unit) {
                val window = activity.window
                val originalBrightness = window.attributes.screenBrightness
                window.attributes = window.attributes.apply {
                    screenBrightness = 1f
                }
                onDispose {
                    window.attributes = window.attributes.apply {
                        screenBrightness = originalBrightness
                    }
                }
            }
        }
        composeRule.waitUntil(timeoutMillis = 2000) {
            activity.window.attributes.screenBrightness == 1f
        }
        assertEquals(1f, activity.window.attributes.screenBrightness)
    }
}