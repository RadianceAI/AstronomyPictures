package by.radiance.space.pictures.presenter.utils

import android.widget.SeekBar
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import by.radiance.space.pictures.presenter.navigation.ScreenType

fun SeekBar.onProgressChanged(listener: (progress: Int, fromUser: Boolean) -> Unit) {
    setOnSeekBarChangeListener(
        object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                listener(p1, p2)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        }
    )
}

fun NavGraphBuilder.composableFromType(
    screenType: ScreenType,
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable(route = screenType.route, arguments = screenType.arguments, content = content)
}

@Composable
fun Int?.stringResource(): String? {
    if (this == null) return null

    return stringResource(this)
}