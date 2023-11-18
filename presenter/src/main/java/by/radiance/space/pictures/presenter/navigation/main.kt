package by.radiance.space.pictures.presenter.navigation

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import by.radiance.space.pictures.domain.entity.Id
import by.radiance.space.pictures.presenter.ui.abount.About
import by.radiance.space.pictures.presenter.navigation.route.TodayRoute
import by.radiance.space.pictures.presenter.ui.picture.PictureDetails
import by.radiance.space.pictures.presenter.ui.screen.ScreenType
import by.radiance.space.pictures.presenter.ui.theme.AstronomyPicturesTheme
import by.radiance.space.pictures.presenter.ui.theme.CardGray
import by.radiance.space.pictures.presenter.ui.utils.AnimatedVisibilityBottomNavigation
import by.radiance.space.pictures.presenter.ui.utils.BottomNavigationScreenItem
import by.radiance.space.pictures.presenter.ui.utils.Rectangle
import by.radiance.space.pictures.presenter.ui.utils.ScaffoldWithConstraints
import by.radiance.space.pictures.presenter.ui.utils.WindowSize
import by.radiance.space.pictures.presenter.ui.utils.heightWindowSize
import by.radiance.space.pictures.presenter.utils.GsonWrapper
import by.radiance.space.pictures.presenter.viewModel.DetailsViewModel
import by.radiance.space.pictures.presenter.viewModel.ListViewModel
import by.radiance.space.pictures.presenter.viewModel.TodayViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

@Composable
fun MainComposable(
    bottomMenu: List<ScreenType>,
    activity: Activity,
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
                    innerPadding = innerPadding,
                    heightWindowSize = heightWindowSize,
                    activity = activity,
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
    innerPadding: PaddingValues,
    heightWindowSize: WindowSize,
    activity: Activity,
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
            composable(route = ScreenType.Today.route) {
                updateBottomBarVisibility(true)
                //todo: use router from routers disct
                TodayRoute(
                    viewModel = activity.viewModel<TodayViewModel>(),
                ).Route(navController, heightWindowSize)
            }
            composable(route = ScreenType.Collection.route) {
                updateBottomBarVisibility(true)
                val viewModel by remember { activity.viewModel<ListViewModel>() }
            }
            composable(route = ScreenType.About.route) {
                updateBottomBarVisibility(true)
                About()
            }
            composable(
                route = "details/{pictureId}",
                arguments = listOf(navArgument("pictureId") {
                    type = NavType.StringType
                })
            ) {
                updateBottomBarVisibility(false)

                val id = it.arguments?.getString("pictureId")?.let { idString ->
                    GsonWrapper.gson.fromJson(idString, Id::class.java)
                }!!

                val viewModel by remember { activity.viewModel<DetailsViewModel>() }

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

