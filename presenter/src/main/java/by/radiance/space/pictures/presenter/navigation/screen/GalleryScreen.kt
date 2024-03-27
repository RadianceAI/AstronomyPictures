package by.radiance.space.pictures.presenter.navigation.screen

import android.os.Bundle
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import by.radiance.space.pictures.presenter.navigation.Router
import by.radiance.space.pictures.presenter.navigation.screen.base.Screen
import by.radiance.space.pictures.presenter.ui.gallery.GalleryView
import by.radiance.space.pictures.presenter.ui.utils.WindowSize
import by.radiance.space.pictures.presenter.viewModel.GalleryViewModel
import org.koin.androidx.compose.getViewModel
import kotlin.math.truncate

class GalleryScreen : Screen {
    @Composable
    override fun View(
        router: Router,
        arguments: Bundle?,
        heightWindowSize: WindowSize,
    ) {
        val viewModel = getViewModel<GalleryViewModel>()

        GalleryView(
            pictures = viewModel.pictures,
            scrollTo = viewModel.scrollTo,
            startDate = viewModel.startDate,
            endDate = viewModel.endDate,
            staggered = true,
            cellCount = 2,
            onClick = { id ->
                router.toDetailsScreen(id)
            },
            onDateSelected = { date ->
                viewModel.onDateSelected(date)
            },
        )
    }
}