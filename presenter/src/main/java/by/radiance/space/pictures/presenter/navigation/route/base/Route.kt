package by.radiance.space.pictures.presenter.navigation.route.base

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import by.radiance.space.pictures.presenter.ui.utils.WindowSize

abstract class Route<T : ViewModel>(
    protected val lazyViewModel: Lazy<T>,
) {
    abstract val isNavigationBarVisible: Boolean

    @Composable
    abstract fun Route(
        navController: NavHostController,
        arguments: Bundle?,
        heightWindowSize: WindowSize,
    )
}