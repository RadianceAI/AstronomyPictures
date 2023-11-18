package by.radiance.space.pictures.presenter.navigation.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.presenter.navigation.route.base.Route
import by.radiance.space.pictures.presenter.ui.today.TodayPictureScreen
import by.radiance.space.pictures.presenter.ui.utils.WindowSize
import by.radiance.space.pictures.presenter.utils.GsonWrapper
import by.radiance.space.pictures.presenter.viewModel.TodayViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class TodayRoute(
    viewModel: Lazy<TodayViewModel>,
) : Route<TodayViewModel>(viewModel) {

    @Composable
    override fun Route(
        navController: NavHostController,
        heightWindowSize: WindowSize,
    ) {
        val viewModel by remember { viewModel }

        val today by remember { viewModel.today }.collectAsState()

        val like: (Picture) -> Unit = { picture ->
        }

        val click: (Picture) -> Unit = { picture ->
            val json = GsonWrapper.gson.toJson(picture.id)
            navController.navigate("details/$json")
        }

        TodayPictureScreen(
            heightWindowSize = heightWindowSize,
            picture = today,
            onClick = click,
            onLike = like,
        )
    }
}

