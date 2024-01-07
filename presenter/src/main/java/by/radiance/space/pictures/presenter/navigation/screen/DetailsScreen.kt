package by.radiance.space.pictures.presenter.navigation.screen

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import by.radiance.space.pictures.domain.entity.Id
import by.radiance.space.pictures.presenter.navigation.Router
import by.radiance.space.pictures.presenter.navigation.ScreenType
import by.radiance.space.pictures.presenter.navigation.screen.base.Screen
import by.radiance.space.pictures.presenter.ui.details.PictureDetailsView
import by.radiance.space.pictures.presenter.ui.error.ErrorCard
import by.radiance.space.pictures.presenter.ui.utils.WindowSize
import by.radiance.space.pictures.presenter.viewModel.DetailsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.compose.getViewModel

@ExperimentalCoroutinesApi
class DetailsScreen : Screen {
    override val isNavigationBarVisible: Boolean = false

    @Composable
    override fun View(
        router: Router,
        arguments: Bundle?,
        heightWindowSize: WindowSize,
    ) {
        val id = arguments?.getString(ScreenType.pictureIdArgument.name)?.let(::Id)

        if (id == null) {
            ErrorCard(error = "Something went wrong")
            return
        }

        val viewModel = getViewModel<DetailsViewModel>()

        val picture by remember { viewModel.picture(id) }.collectAsState()
        val progress by remember { viewModel.progress }.collectAsState()

        PictureDetailsView(
            pictureUiState = picture,
            progress = progress,
            heightWindowSize = heightWindowSize,
            onShare = { image -> viewModel.share(image) },
            onSystemWallpaper = { wallpaper ->
                viewModel.setSystemWallpaper(
                    wallpaper
                )
            },
            onLockScreenWallpaper = { wallpaper ->
                viewModel.setLockScreenWallpaper(
                    wallpaper
                )
            },
            onAllWallpaper = { wallpaper ->
                viewModel.setAllWallpaper(wallpaper)
            }
        )
    }
}