package by.radiance.space.pictures.presenter.navigation.screen

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import by.radiance.space.pictures.presenter.navigation.Router
import by.radiance.space.pictures.presenter.navigation.screen.base.Screen
import by.radiance.space.pictures.presenter.ui.gallery.GalleryView
import by.radiance.space.pictures.presenter.ui.utils.WindowSize
import by.radiance.space.pictures.presenter.viewModel.GalleryViewModel

class GalleryScreen(
    viewModel: Lazy<GalleryViewModel>,
) : Screen<GalleryViewModel>(viewModel) {

    override val isNavigationBarVisible: Boolean = true

    @Composable
    override fun View(
        router: Router,
        arguments: Bundle?,
        heightWindowSize: WindowSize,
    ) {
        val viewModel by remember { lazyViewModel }

        GalleryView(
            pictures = viewModel.pictures,
            cellCount = 2,
            onClick = { picture ->
                router.toDetailsScreen(picture.id)
            },
        )
    }
}