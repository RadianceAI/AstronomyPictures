package by.radiance.space.pictures.presenter.navigation.route

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import by.radiance.space.pictures.domain.entity.Id
import by.radiance.space.pictures.presenter.navigation.route.base.Route
import by.radiance.space.pictures.presenter.ui.picture.PictureDetails
import by.radiance.space.pictures.presenter.ui.utils.WindowSize
import by.radiance.space.pictures.presenter.utils.GsonWrapper
import by.radiance.space.pictures.presenter.viewModel.DetailsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class DetailsRoute(
    val viewModel: Lazy<DetailsViewModel>
) : Route<DetailsViewModel>(viewModel) {
    override val isNavigationBarVisible: Boolean = false

    @Composable
    override fun Route(
        navController: NavHostController,
        arguments: Bundle?,
        heightWindowSize: WindowSize
    ) {
        val id = arguments?.getString("pictureId")?.let { idString ->
            GsonWrapper.gson.fromJson(idString, Id::class.java)
        }!!

        val viewModel by remember { lazyViewModel }

        val picture by remember { viewModel.picture(id) }.collectAsState()
        val progress by remember { viewModel.progress }.collectAsState()

        PictureDetails(
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