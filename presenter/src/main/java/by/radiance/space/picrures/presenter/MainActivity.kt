package by.radiance.space.picrures.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import by.radiance.space.picrures.presenter.ui.abount.About
import by.radiance.space.picrures.presenter.ui.collection.Collection
import by.radiance.space.picrures.presenter.ui.new.NewPictures
import by.radiance.space.picrures.presenter.ui.picture.PictureDetails
import by.radiance.space.picrures.presenter.ui.screen.Screen
import by.radiance.space.picrures.presenter.ui.theme.AstronomyPicturesTheme
import by.radiance.space.picrures.presenter.ui.theme.CardGray
import by.radiance.space.picrures.presenter.ui.utils.*
import by.radiance.space.picrures.presenter.utils.GsonWrapper
import by.radiance.space.picrures.presenter.viewModel.DetailsViewModel
import by.radiance.space.picrures.presenter.viewModel.ListViewModel
import by.radiance.space.picrures.presenter.viewModel.NewViewModel
import by.radiance.space.pictures.domain.entity.Id
import by.radiance.space.pictures.domain.entity.Picture
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val screens = listOf(
            Screen.New,
            Screen.Collection,
            Screen.About,
        )

        setContent {
            AstronomyPicturesTheme {
                var bottomBarState by rememberSaveable { (mutableStateOf(true)) }
                val navController = rememberNavController()

                ScaffoldWithConstraints(
                    bottomBar = {
                        AnimatedVisibilityBottomNavigation(
                            visible = bottomBarState && heightWindowSize != WindowSize.Compact,
                            modifier = Modifier
                                .padding(5.dp)
                                .clip(Rectangle),
                            backgroundColor = CardGray,
                        ) {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination

                            screens.forEach { screen ->
                                BottomNavigationScreenItem(
                                    screen,
                                    currentDestination,
                                    navController
                                )
                            }
                        }
                    },
                    content = { innerPadding ->
                        Row {
                            if (bottomBarState && this@ScaffoldWithConstraints.heightWindowSize == WindowSize.Compact) {
                                Column(
                                    modifier = Modifier
                                        .padding(5.dp)
                                        .clip(RoundedCornerShape(5.dp))
                                        .background(CardGray),
                                ) {
                                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                                    val currentDestination = navBackStackEntry?.destination

                                    screens.forEach { screen ->
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
                                startDestination = Screen.New.route,
                                modifier = Modifier.padding(innerPadding)
                            ) {
                                composable(route = Screen.New.route) {
                                    bottomBarState = true
                                    val viewModel by remember { viewModel<NewViewModel>() }

                                    val today by remember { viewModel.today }.collectAsState()
                                    val random by remember { viewModel.random }.collectAsState()

                                    val like: (Picture) -> Unit = { picture ->
                                        viewModel.save(picture)
                                    }

                                    val click: (Picture) -> Unit = { picture ->
                                        val json = GsonWrapper.gson.toJson(picture.id)
                                        navController.navigate("details/$json")
                                    }

                                    NewPictures(
                                        this@ScaffoldWithConstraints.heightWindowSize,
                                        todayPicture = today,
                                        randomPicture = random,
                                        onClick = click,
                                        onLike = like,
                                    )
                                }
                                composable(route = Screen.Collection.route) {
                                    bottomBarState = true
                                    val viewModel by remember { viewModel<ListViewModel>() }

                                    val list by remember { viewModel.list }.collectAsState()

                                    val click: (Picture) -> Unit = { picture ->
                                        val json = GsonWrapper.gson.toJson(picture.id)
                                        navController.navigate("details/$json")
                                    }

                                    Collection(
                                        this@ScaffoldWithConstraints.heightWindowSize,
                                        list = list,
                                        onClick = click,
                                    )
                                }
                                composable(route = Screen.About.route) {
                                    bottomBarState = true
                                    About()
                                }
                                composable(
                                    route = "details/{pictureId}",
                                    arguments = listOf(navArgument("pictureId") {
                                        type = NavType.StringType
                                    })
                                ) {
                                    bottomBarState = false

                                    val id = it.arguments?.getString("pictureId")?.let { idString ->
                                        GsonWrapper.gson.fromJson(idString, Id::class.java)
                                    }!!

                                    val viewModel by remember { viewModel<DetailsViewModel>() }

                                    val picture by remember { viewModel.picture(id) }.collectAsState()

                                    PictureDetails(
                                        this@ScaffoldWithConstraints.heightWindowSize,
                                        pictureUiState = picture,
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
                )
            }
        }
    }
}