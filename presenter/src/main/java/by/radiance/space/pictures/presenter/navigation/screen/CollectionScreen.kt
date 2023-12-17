package by.radiance.space.pictures.presenter.navigation.screen

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import by.radiance.space.pictures.presenter.navigation.Router
import by.radiance.space.pictures.presenter.navigation.screen.base.Screen
import by.radiance.space.pictures.presenter.ui.utils.WindowSize
import by.radiance.space.pictures.presenter.viewModel.ListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class CollectionScreen(
    viewModel: Lazy<ListViewModel>,
) : Screen<ListViewModel>(viewModel) {

    override val isNavigationBarVisible: Boolean = true

    @Composable
    override fun View(
        router: Router,
        arguments: Bundle?,
        heightWindowSize: WindowSize
    ) {
        val viewModel by remember { lazyViewModel }
    }
}