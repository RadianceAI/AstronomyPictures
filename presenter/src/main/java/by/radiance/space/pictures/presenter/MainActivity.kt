package by.radiance.space.pictures.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import by.radiance.space.pictures.presenter.navigation.Router
import by.radiance.space.pictures.presenter.navigation.ScreenType
import by.radiance.space.pictures.presenter.navigation.screen.AboutScreen
import by.radiance.space.pictures.presenter.navigation.screen.CollectionScreen
import by.radiance.space.pictures.presenter.navigation.screen.DetailsScreen
import by.radiance.space.pictures.presenter.navigation.screen.GalleryScreen
import by.radiance.space.pictures.presenter.ui.Root
import kotlinx.coroutines.ExperimentalCoroutinesApi

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bottomMenu = listOf(
            ScreenType.Gallery,
            ScreenType.About,
        )

        val routes = mapOf(
            ScreenType.Collection to CollectionScreen(),
            ScreenType.About to AboutScreen(),
            ScreenType.Details to DetailsScreen(),
            ScreenType.Gallery to GalleryScreen(),
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