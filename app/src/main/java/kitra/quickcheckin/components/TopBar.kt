package kitra.quickcheckin.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp

/**
 * 本 App 应当使用这个 TopAppBar，它提供了统一的阴影效果。
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopAppBar(modifier: Modifier = Modifier, title: @Composable () -> Unit = {}, navigationIcon: @Composable () -> Unit = {}, actions: @Composable (RowScope.() -> Unit) = {}) {
    TopAppBar(
        title = title,
        navigationIcon = navigationIcon,
        actions = actions,
        modifier = modifier.shadow(elevation = 4.dp)
    )
}