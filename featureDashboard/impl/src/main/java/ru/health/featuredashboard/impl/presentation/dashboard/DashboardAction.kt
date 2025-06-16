package ru.health.featuredashboard.impl.presentation.dashboard

import android.content.Context
import androidx.compose.ui.graphics.ImageBitmap

internal sealed interface DashboardAction {

    data object Init : DashboardAction

    data class OnShareClick(val context: Context, val bitmap: ImageBitmap, val text: String) : DashboardAction
}