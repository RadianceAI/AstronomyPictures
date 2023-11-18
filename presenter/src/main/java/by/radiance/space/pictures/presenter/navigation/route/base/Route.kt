package by.radiance.space.pictures.presenter.navigation.route.base

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import by.radiance.space.pictures.presenter.ui.utils.WindowSize

abstract class Route <T: ViewModel> (
    protected val viewModel: Lazy<T>,
) {

    @Composable
    abstract fun Route(navController: NavHostController, heightWindowSize: WindowSize)
}