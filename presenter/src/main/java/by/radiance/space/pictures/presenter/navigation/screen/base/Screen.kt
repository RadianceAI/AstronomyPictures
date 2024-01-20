package by.radiance.space.pictures.presenter.navigation.screen.base

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import by.radiance.space.pictures.presenter.navigation.Router
import by.radiance.space.pictures.presenter.ui.utils.WindowSize

interface Screen {
    @Composable
    fun View(
        router: Router,
        arguments: Bundle?,
        heightWindowSize: WindowSize,
    )
}