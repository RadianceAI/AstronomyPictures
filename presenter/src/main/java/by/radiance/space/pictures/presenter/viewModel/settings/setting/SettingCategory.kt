package by.radiance.space.pictures.presenter.viewModel.settings.setting

import androidx.compose.ui.graphics.vector.ImageVector

data class SettingCategory(
    val icon: ImageVector,
    val title: Int,
    val description: Int?,
    val settings: List<Setting>
)
