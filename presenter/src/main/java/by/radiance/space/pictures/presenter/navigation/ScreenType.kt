package by.radiance.space.pictures.presenter.navigation

import android.transition.Scene
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Collections
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MonitorHeart
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import by.radiance.space.pictures.presenter.R

sealed class ScreenType(
    val id: String,
    val arguments: List<NamedNavArgument> = emptyList(),
    val route: String = "$id/${arguments.toRouteArguments()}",
    @StringRes val title: Int? = null,
    val icon: ImageVector? = null,
) {

    data object Collection : ScreenType(
        id = "collection",
        title = R.string.collection_screen,
        icon = Icons.Filled.Favorite,
    )

    data object About : ScreenType(
        id = "about",
        title = R.string.about,
        icon = Icons.Filled.Info,
    )

    data object Details : ScreenType(
        id = "details",
        arguments = listOf(pictureIdArgument),
    )

    data object Gallery : ScreenType(
        id = "gallery",
        title = R.string.gallery,
        icon = Icons.Filled.PhotoLibrary,
    )

    companion object {
        val pictureIdArgument = navArgument("pictureId") { type = NavType.StringType }

        private fun List<NamedNavArgument>.toRouteArguments(): String {
            return this.joinToString(separator = "/") { argument -> "{${argument.name}}" }
        }
    }
}