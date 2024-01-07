package by.radiance.space.pictures.presenter.navigation.screen

import android.os.Bundle
import androidx.compose.runtime.Composable
import by.radiance.space.pictures.presenter.navigation.Router
import by.radiance.space.pictures.presenter.navigation.screen.base.Screen
import by.radiance.space.pictures.presenter.ui.abount.AboutView
import by.radiance.space.pictures.presenter.ui.utils.WindowSize

class AboutScreen : Screen {

    override val isNavigationBarVisible: Boolean = true

    @Composable
    override fun View(
        router: Router,
        arguments: Bundle?,
        heightWindowSize: WindowSize
    ) {
        AboutView()
    }
}