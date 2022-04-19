package by.radiance.space.picrures.presenter.ui.utils

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun ScaffoldWithConstraints(
    bottomBar: @Composable BoxWithConstraintsScope.() -> Unit,
    content: @Composable BoxWithConstraintsScope.(PaddingValues) -> Unit
) {
    BoxWithConstraints {
        Scaffold(
            bottomBar = {
                bottomBar()
            },
            content = {
                content(it)
            }
        )
    }
}

enum class WindowSize {
    Compact,
    Medium,
    Expanded,
}

val BoxWithConstraintsScope.heightWindowSize: WindowSize
    get() = when {
        maxHeight < 480.dp -> WindowSize.Compact
        maxHeight < 900.dp -> WindowSize.Medium
        else -> WindowSize.Expanded
    }

val BoxWithConstraintsScope.widthWindowSize: WindowSize
    get() = when {
        maxWidth < 600.dp -> WindowSize.Compact
        maxWidth < 840.dp -> WindowSize.Medium
        else -> WindowSize.Expanded
    }

