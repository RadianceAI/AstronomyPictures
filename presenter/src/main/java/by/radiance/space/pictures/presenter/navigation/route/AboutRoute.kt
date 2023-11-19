package by.radiance.space.pictures.presenter.navigation.route

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import by.radiance.space.pictures.presenter.navigation.route.base.Route
import by.radiance.space.pictures.presenter.ui.abount.About
import by.radiance.space.pictures.presenter.ui.utils.WindowSize
import by.radiance.space.pictures.presenter.viewModel.AboutViewModel

class AboutRoute(
    viewModel: Lazy<AboutViewModel>,
) : Route<AboutViewModel>(viewModel) {

    override val isNavigationBarVisible: Boolean = true

    @Composable
    override fun Route(
        navController: NavHostController,
        arguments: Bundle?,
        heightWindowSize: WindowSize
    ) {
        val viewModel by remember { lazyViewModel }
        About()
    }
}