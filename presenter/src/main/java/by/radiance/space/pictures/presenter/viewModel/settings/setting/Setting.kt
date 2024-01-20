package by.radiance.space.pictures.presenter.viewModel.settings.setting

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Setting(
    val id: String,
    val icon: ImageVector,
    val title: Int,
    val description: Int?,
) {
    interface SettingChange

    class Picker(
        id: String,
        icon: ImageVector,
        title: Int,
        description: Int?,
        val options: List<Option>,
        val selectedItemIndex: Int,
    ) : Setting(id, icon, title, description) {

        fun change(index: Int) : SettingChange {
            return Change(index)
        }

        data class Option(
            val title: Int?,
            val description: Int?,
            val view: @Composable (isSelected: Boolean) -> Unit,
        )

        data class Change(val index: Int) : SettingChange
    }

    class Switch(
        id: String,
        icon: ImageVector,
        title: Int,
        description: Int?,
        val isEnabled: Boolean,
        val preview: (@Composable () -> Unit)?,
    ) : Setting(id, icon, title, description) {

        fun change(isEnabled: Boolean) : SettingChange {
            return Change(isEnabled)
        }

        data class Change(val isEnabled: Boolean) : SettingChange
    }
}