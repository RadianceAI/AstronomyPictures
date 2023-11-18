package by.radiance.space.pictures.presenter.ui.screen

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Collections
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector
import by.radiance.space.pictures.presenter.R

sealed class ScreenType(val route: String, @StringRes val title: Int, val icon: ImageVector) {
    data object Today: ScreenType("today", R.string.today_screen, Icons.Filled.Image)
    data object Collection: ScreenType("collection", R.string.collection, Icons.Filled.Collections)
    data object About: ScreenType("about", R.string.about, Icons.Filled.Info)
}