package by.radiance.space.pictures.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import by.radiance.space.pictures.presenter.navigation.Router
import by.radiance.space.pictures.presenter.navigation.ScreenType
import by.radiance.space.pictures.presenter.ui.Root
import by.radiance.space.pictures.presenter.navigation.screen.AboutScreen
import by.radiance.space.pictures.presenter.navigation.screen.CollectionScreen
import by.radiance.space.pictures.presenter.navigation.screen.DetailsScreen
import by.radiance.space.pictures.presenter.navigation.screen.TodayScreen
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
            ScreenType.Today to TodayScreen(viewModel()),
            ScreenType.Collection to CollectionScreen(viewModel()),
            ScreenType.About to AboutScreen(viewModel()),
            ScreenType.Details to DetailsScreen(viewModel())
        )

        setContent {
            val navController = rememberNavController()
            val router = Router(
                navController = navController,
                routes = routes,
                bottomMenu = bottomMenu,
            )

            Root(
                router = router,
            )
        }
    }
}