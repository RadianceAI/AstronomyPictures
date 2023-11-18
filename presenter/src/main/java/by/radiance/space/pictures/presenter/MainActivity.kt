package by.radiance.space.pictures.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import by.radiance.space.pictures.presenter.ui.screen.ScreenType
import by.radiance.space.pictures.presenter.navigation.MainComposable
import by.radiance.space.pictures.presenter.navigation.route.TodayRoute
import by.radiance.space.pictures.presenter.viewModel.TodayViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bottomMenu = listOf(
            ScreenType.Today,
            ScreenType.Collection,
            ScreenType.About,
        )

        val routes = mapOf(
            ScreenType.Today to TodayRoute(viewModel<TodayViewModel>()),
            //todo: Collection, About, Details
        )

        //todo pass to MainComposable

        setContent {
            MainComposable(bottomMenu = bottomMenu, activity = this)
        }
    }
}