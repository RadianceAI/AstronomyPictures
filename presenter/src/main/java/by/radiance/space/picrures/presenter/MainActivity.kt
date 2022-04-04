package by.radiance.space.picrures.presenter

import android.os.Bundle
import android.util.Log
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
import by.radiance.space.picrures.presenter.picture.PictureDetails
import by.radiance.space.picrures.presenter.ui.theme.AstronomyPicturesTheme
import by.radiance.space.picrures.presenter.utils.GsonWrapper
import by.radiance.space.picrures.presenter.viewModel.DetailsViewModel
import by.radiance.space.picrures.presenter.viewModel.ListViewModel
import by.radiance.space.pictures.domain.entity.Id
import by.radiance.space.pictures.domain.entity.Picture
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalCoroutinesApi::class)
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

                        val like: (Picture) -> Unit = { picture ->
                            viewModel.save(picture)
                        }

                        val click: (Picture) -> Unit = { picture ->
                            val json = GsonWrapper.gson.toJson(picture.id)
                            navController.navigate("details/$json")
                        }

                        viewModel.init()

                        PictureList(
                            todayPicture = today,
                            randomPicture = random,
                            savedList = list,
                            onClick = click,
                            onLike = like,
                        )
                    }

                    composable(
                        route = "details/{pictureId}",
                        arguments = listOf(navArgument("pictureId") { type = NavType.StringType })
                    ) {
                        val id = it.arguments?.getString("pictureId")?.let { idString ->
                            GsonWrapper.gson.fromJson(idString, Id::class.java)
                        }!!

                        Log.d("WFT_WFT", "onCreate: ${id}")

                        val viewModel by viewModel<DetailsViewModel>()

                        val picture by viewModel.picture.collectAsState()

                        viewModel.init(id)

                        PictureDetails(picture = picture, onLike = {})
                    }
                }
            }
        }
    }
}