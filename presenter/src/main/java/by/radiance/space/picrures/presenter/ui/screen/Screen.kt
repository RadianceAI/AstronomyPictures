package by.radiance.space.picrures.presenter.ui.screen

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Collections
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector
import by.radiance.space.picrures.presenter.R

sealed class Screen(val route: String, @StringRes val title: Int, val icon: ImageVector) {
    object New: Screen("new", R.string.new_screen, Icons.Filled.Image)
    object Collection: Screen("collection", R.string.collection, Icons.Filled.Collections)
    object About: Screen("about", R.string.about, Icons.Filled.Info)
}