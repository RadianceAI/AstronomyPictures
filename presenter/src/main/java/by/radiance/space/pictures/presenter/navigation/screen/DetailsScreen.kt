package by.radiance.space.pictures.presenter.navigation.screen

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import by.radiance.space.pictures.domain.entity.Id
import by.radiance.space.pictures.presenter.navigation.Router
import by.radiance.space.pictures.presenter.navigation.screen.base.Screen
import by.radiance.space.pictures.presenter.ui.picture.PictureDetailsView
import by.radiance.space.pictures.presenter.ui.utils.WindowSize
import by.radiance.space.pictures.presenter.utils.GsonWrapper
import by.radiance.space.pictures.presenter.viewModel.DetailsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class DetailsScreen(
    viewModel: Lazy<DetailsViewModel>
) : Screen<DetailsViewModel>(viewModel) {
    override val isNavigationBarVisible: Boolean = false

    @Composable
    override fun View(
        router: Router,
        arguments: Bundle?,
        heightWindowSize: WindowSize
    ) {
        val id = arguments?.getString("pictureId")?.let { idString ->
            GsonWrapper.gson.fromJson(idString, Id::class.java)
        }!!

        val viewModel by remember { lazyViewModel }

        val picture by remember { viewModel.picture(id) }.collectAsState()
        val progress by remember { viewModel.progress }.collectAsState()

        PictureDetailsView(
            heightWindowSize = heightWindowSize,
            pictureUiState = picture,
            progress = progress,
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