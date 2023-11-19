package by.radiance.space.pictures.presenter.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import by.radiance.space.pictures.presenter.navigation.route.base.Route
import by.radiance.space.pictures.presenter.ui.theme.AstronomyPicturesTheme
import by.radiance.space.pictures.presenter.ui.theme.CardGray
import by.radiance.space.pictures.presenter.ui.utils.AnimatedVisibilityBottomNavigation
import by.radiance.space.pictures.presenter.ui.utils.BottomNavigationScreenItem
import by.radiance.space.pictures.presenter.ui.utils.Rectangle
import by.radiance.space.pictures.presenter.ui.utils.ScaffoldWithConstraints
import by.radiance.space.pictures.presenter.ui.utils.WindowSize
import by.radiance.space.pictures.presenter.ui.utils.heightWindowSize
import by.radiance.space.pictures.presenter.utils.composableFromType
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Composable
fun Root(
    bottomMenu: List<ScreenType>,
    routes: Map<ScreenType, Route<out ViewModel>>,
) {
    AstronomyPicturesTheme(
        darkTheme = true
    ) {
        var bottomBarState by rememberSaveable { (mutableStateOf(true)) }
        val navController = rememberNavController()

        ScaffoldWithConstraints(
            bottomBar = {
                BottomBar(
                    bottomBarState = bottomBarState,
                    navController = navController,
                    bottomMenu = bottomMenu,
                )
            },
            content = { innerPadding ->
                Content(
                    bottomBarState = bottomBarState,
                    navController = navController,
                    bottomMenu = bottomMenu,
                    routes = routes,
                    innerPadding = innerPadding,
                    heightWindowSize = heightWindowSize,
                    updateBottomBarVisibility = { isVisible ->
                        bottomBarState = isVisible
                    }
                )
            }
        )
    }
}

@Composable
@OptIn(ExperimentalCoroutinesApi::class)
private fun Content(
    bottomBarState: Boolean,
    navController: NavHostController,
    bottomMenu: List<ScreenType>,
    routes: Map<ScreenType, Route<out ViewModel>>,
    innerPadding: PaddingValues,
    heightWindowSize: WindowSize,
    updateBottomBarVisibility: (Boolean) -> Unit,
) {
    Row {
        if (bottomBarState && heightWindowSize == WindowSize.Compact) {
            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(CardGray),
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                bottomMenu.forEach { screen ->
                    BottomNavigationScreenItem(
                        screen,
                        currentDestination,
                        navController
                    )
                }
            }
        }
        NavHost(
            navController = navController,
            startDestination = ScreenType.Today.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            for ((screenType, routeDestination) in routes) {
                composableFromType(screenType) {
                    updateBottomBarVisibility(routeDestination.isNavigationBarVisible)
                    routeDestination.Route(navController, it.arguments, heightWindowSize)
                }
            }
        }
    }
}

@Composable
private fun BoxWithConstraintsScope.BottomBar(
    bottomBarState: Boolean,
    navController: NavHostController,
    bottomMenu: List<ScreenType>
) {
    AnimatedVisibilityBottomNavigation(
        visible = bottomBarState && heightWindowSize != WindowSize.Compact,
        modifier = Modifier
            .padding(5.dp)
            .clip(Rectangle),
        backgroundColor = CardGray,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        bottomMenu.forEach { screen ->
            BottomNavigationScreenItem(
                screen,
                currentDestination,
                navController
            )
        }
    }
}

