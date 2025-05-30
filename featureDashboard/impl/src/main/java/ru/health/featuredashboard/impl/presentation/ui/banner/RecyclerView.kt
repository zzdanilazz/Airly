package ru.health.featuredashboard.impl.presentation.ui.banner

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

@Composable
internal fun RecyclerView(
    modifier: Modifier = Modifier,
    adapter: RecyclerView.Adapter<*>
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            RecyclerView(context).apply {
                layoutManager = LinearLayoutManager(context)
                this.adapter = adapter
            }
        }
    )
}