package by.radiance.space.pictures.presenter.ui.settings.model

import androidx.compose.ui.graphics.vector.ImageVector

data class SettingCategory(
    val icon: ImageVector,
    val title: Int,
    val description: Int?,
    val settings: List<Setting>
)
