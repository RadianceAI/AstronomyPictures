package by.radiance.space.pictures.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import by.radiance.space.pictures.domain.entity.settings.ApplicationTheme
import by.radiance.space.pictures.domain.repository.SettingRepository
import by.radiance.space.pictures.presenter.navigation.Router
import by.radiance.space.pictures.presenter.navigation.ScreenType
import by.radiance.space.pictures.presenter.navigation.screen.AboutScreen
import by.radiance.space.pictures.presenter.navigation.screen.CollectionScreen
import by.radiance.space.pictures.presenter.navigation.screen.DetailsScreen
import by.radiance.space.pictures.presenter.navigation.screen.GalleryScreen
import by.radiance.space.pictures.presenter.navigation.screen.SettingsScreen
import by.radiance.space.pictures.presenter.ui.Root
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.compose.get

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bottomMenu = listOf(
            ScreenType.Gallery,
            ScreenType.About,
            ScreenType.Settings,
        )

        val routes = mapOf(
            ScreenType.Collection to CollectionScreen(),
            ScreenType.About to AboutScreen(),
            ScreenType.Details to DetailsScreen(),
            ScreenType.Gallery to GalleryScreen(),
            ScreenType.Settings to SettingsScreen(),
        )

        setContent {
            val navController = rememberNavController()
            val theme by get<SettingRepository>().theme.collectAsState(ApplicationTheme.System)

            val router = Router(
                navController = navController,
                routes = routes,
                bottomMenu = bottomMenu,
            )

            Root(
                darkTheme = when (theme) {
                    ApplicationTheme.Dark -> true
                    ApplicationTheme.Light -> false
                    ApplicationTheme.System -> isSystemInDarkTheme()
                },
                router = router,
            )
        }
    }
}