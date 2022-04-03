package by.radiance.space.picrures.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import by.radiance.space.picrures.presenter.list.PictureList
import by.radiance.space.picrures.presenter.ui.theme.AstronomyPicturesTheme
import by.radiance.space.picrures.presenter.viewModel.ListViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AstronomyPicturesTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "list") {
                    composable(route = "list") {
                        val viewModel by viewModel<ListViewModel>()


                        val today by viewModel.today.collectAsState()
                        val random by viewModel.random.collectAsState()
                        val list by viewModel.list.collectAsState()

                        viewModel.init()

                        PictureList(
                            todayPicture = today,
                            randomPicture = random,
                            savedList = list,
                            onClick = {},
                            onLike = {}
                        )
                    }

                    composable(
                        route = "details/{pictureId}",
                        arguments = listOf(navArgument("pictureId") { NavType.LongType })
                    ) {

                    }
                }
            }
        }
    }
}