package by.radiance.space.pictures.presenter.navigation.screen

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import by.radiance.space.pictures.domain.entity.Id
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.presenter.navigation.Router
import by.radiance.space.pictures.presenter.navigation.screen.base.Screen
import by.radiance.space.pictures.presenter.ui.today.TodayPictureScreenView
import by.radiance.space.pictures.presenter.ui.utils.WindowSize
import by.radiance.space.pictures.presenter.viewModel.TodayViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class TodayScreen(
    viewModel: Lazy<TodayViewModel>,
) : Screen<TodayViewModel>(viewModel) {

    override val isNavigationBarVisible: Boolean = true

    @Composable
    override fun View(
        router: Router,
        arguments: Bundle?,
        heightWindowSize: WindowSize,
    ) {
        val viewModel by remember { lazyViewModel }

        val today by remember { viewModel.today }.collectAsState()

        val like: (Picture) -> Unit = { picture ->
        }

        val click: (Id) -> Unit = { id ->
            router.toDetailsScreen(id)
        }

        TodayPictureScreenView(
            picture = today,
            onClick = click,
            onLike = like,
        )
    }
}

