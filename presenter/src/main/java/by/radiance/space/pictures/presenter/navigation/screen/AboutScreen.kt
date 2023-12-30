package by.radiance.space.pictures.presenter.navigation.screen

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import by.radiance.space.pictures.presenter.navigation.Router
import by.radiance.space.pictures.presenter.navigation.screen.base.Screen
import by.radiance.space.pictures.presenter.ui.abount.AboutView
import by.radiance.space.pictures.presenter.ui.utils.WindowSize
import by.radiance.space.pictures.presenter.viewModel.AboutViewModel

class AboutScreen(
    viewModel: Lazy<AboutViewModel>,
) : Screen<AboutViewModel>(viewModel) {

    override val isNavigationBarVisible: Boolean = true

    @Composable
    override fun View(
        router: Router,
        arguments: Bundle?,
        heightWindowSize: WindowSize
    ) {
        val viewModel by remember { lazyViewModel }
        AboutView()
    }
}