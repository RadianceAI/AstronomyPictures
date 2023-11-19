package by.radiance.space.pictures.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import by.radiance.space.pictures.presenter.navigation.ScreenType
import by.radiance.space.pictures.presenter.navigation.Root
import by.radiance.space.pictures.presenter.navigation.route.AboutRoute
import by.radiance.space.pictures.presenter.navigation.route.CollectionRoute
import by.radiance.space.pictures.presenter.navigation.route.DetailsRoute
import by.radiance.space.pictures.presenter.navigation.route.TodayRoute
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
            ScreenType.Today to TodayRoute(viewModel()),
            ScreenType.Collection to CollectionRoute(viewModel()),
            ScreenType.About to AboutRoute(viewModel()),
            ScreenType.Details to DetailsRoute(viewModel())
        )

        setContent {
            Root(bottomMenu = bottomMenu, routes = routes)
        }
    }
}