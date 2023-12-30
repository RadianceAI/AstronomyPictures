package by.radiance.space.pictures.presenter.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import by.radiance.space.pictures.domain.entity.Id
import by.radiance.space.pictures.presenter.navigation.screen.base.Screen
import by.radiance.space.pictures.presenter.ui.utils.WindowSize
import by.radiance.space.pictures.presenter.utils.composableFromType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class Router(
    private val navController: NavHostController,
    private val routes: Map<ScreenType, Screen<out ViewModel>>,
    val bottomMenu: List<ScreenType>,
) {

    fun currentScreenFlow(): Flow<ScreenType?> {
        return navController.currentBackStackEntryFlow
            .map { currentBackStackEntry ->
                when (currentBackStackEntry.destination.route?.split("/")?.firstOrNull()) {
                    ScreenType.About.id -> ScreenType.About
                    ScreenType.Collection.id -> ScreenType.Collection
                    ScreenType.Today.id -> ScreenType.Today
                    ScreenType.Details.id -> ScreenType.Details
                    ScreenType.Gallery.id -> ScreenType.Gallery
                    else -> null
                }
            }
    }

    @Composable
    fun currentScreenAsState(): State<ScreenType?> {
        val currentScreenType = remember { currentScreenFlow() }
        return currentScreenType.collectAsState(initial = null)
    }

    fun toScreen(screenType: ScreenType, builder: NavOptionsBuilder.() -> Unit) {
        navController.navigate(screenType.route, builder)
    }

    fun toAboutScreen() {
        navController.navigate(ScreenType.About.route)
    }

    fun toTodayScreen() {
        navController.navigate(ScreenType.Today.route)
    }

    fun toCollectionScreen() {
        navController.navigate(ScreenType.Collection.route)
    }

    fun toDetailsScreen(pictureId: Id) {
        navController.navigate("details/${pictureId.date}")
    }

    @Composable
    fun NavigationHost(
        modifier: Modifier,
        updateBottomBarVisibility: (Boolean) -> Unit,
        heightWindowSize: WindowSize,
    ) {
        NavHost(
            navController = navController,
            startDestination = bottomMenu.first().route,
            modifier = modifier,
        ) {
            for ((screenType, routeDestination) in routes) {
                composableFromType(screenType) {
                    updateBottomBarVisibility(routeDestination.isNavigationBarVisible)
                    routeDestination.View(this@Router, it.arguments, heightWindowSize)
                }
            }
        }
    }
}