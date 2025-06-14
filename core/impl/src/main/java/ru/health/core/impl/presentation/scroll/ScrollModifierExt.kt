package ru.health.core.impl.presentation.scroll

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import kotlinx.coroutines.launch

@Composable
fun Modifier.scrollToItemOnFocusChange(
    listState: LazyListState,
    itemIndex: Int
): Modifier {
    val coroutineScope = rememberCoroutineScope()
    return this.onFocusChanged { focusState ->
        if (focusState.isFocused) {
            coroutineScope.launch {
                listState.animateScrollToItem(itemIndex)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SyncScrollBehaviorWithListState(
    listState: LazyListState,
    scrollBehavior: TopAppBarScrollBehavior
) {
    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemScrollOffset }
            .collect { scrollOffset ->
                if (scrollOffset != 0) {
                    scrollBehavior.state.contentOffset = -scrollOffset.toFloat()
                }
            }
    }
}