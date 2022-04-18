package by.radiance.space.picrures.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import by.radiance.space.picrures.presenter.collection.Collection
import by.radiance.space.picrures.presenter.new.NewPictures
import by.radiance.space.picrures.presenter.picture.PictureDetails
import by.radiance.space.picrures.presenter.ui.screen.Screen
import by.radiance.space.picrures.presenter.ui.theme.AstronomyPicturesTheme
import by.radiance.space.picrures.presenter.utils.GsonWrapper
import by.radiance.space.picrures.presenter.viewModel.DetailsViewModel
import by.radiance.space.picrures.presenter.viewModel.ListViewModel
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
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomNavigation {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination
                            screens.forEach { screen ->
                                BottomNavigationItem(
                                    icon = { Icon(screen.icon, stringResource(screen.title)) },
                                    label = { Text(stringResource(screen.title)) },
                                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                    onClick = {
                                        navController.navigate(screen.route) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(navController = navController, startDestination = "new", modifier = Modifier.padding(innerPadding)) {
                        composable(route = Screen.New.route) {
                            val viewModel by remember { viewModel<ListViewModel>() }

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
                                todayPicture = today,
                                randomPicture = random,
                                onClick = click,
                                onLike = like,
                            )
                        }
                        composable(route = Screen.Collection.route) {
                            val viewModel by remember { viewModel<ListViewModel>() }

                            val list by remember { viewModel.list }.collectAsState()

                            val like: (Picture) -> Unit = { picture ->
                                viewModel.save(picture)
                            }

                            val click: (Picture) -> Unit = { picture ->
                                val json = GsonWrapper.gson.toJson(picture.id)
                                navController.navigate("details/$json")
                            }

                            Collection(
                                list = list,
                                onClick = click,
                                onLike = like,
                            )
                        }
                        composable(route = Screen.About.route) {

                        }
                        composable(
                            route = "details/{pictureId}",
                            arguments = listOf(navArgument("pictureId") { type = NavType.StringType })
                        ) {
                            val id = it.arguments?.getString("pictureId")?.let { idString ->
                                GsonWrapper.gson.fromJson(idString, Id::class.java)
                            }!!

                            val viewModel by remember { viewModel<DetailsViewModel>() }

                            val picture by remember { viewModel.picture(id) }.collectAsState()

                            PictureDetails(picture = picture, onLike = {})
                        }
                    }
                }
            }
        }
    }
}