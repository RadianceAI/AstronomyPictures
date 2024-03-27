package by.radiance.space.pictures.presenter.ui.settings.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Setting(
    val id: String,
    val icon: ImageVector,
    val title: Int,
    val description: Int?,
) {
    interface SettingChange

    class Picker<T : Any>(
        id: String,
        icon: ImageVector,
        title: Int,
        description: Int?,
        val options: List<T>,
        val isSelected: (T) -> Boolean,
        val preview: @Composable (T) -> Unit,
    ) : Setting(id, icon, title, description) {

        fun change(index: Int): SettingChange {
            return Change(index)
        }

        data class Change(val index: Int) : SettingChange
    }

    class Slider(
        id: String,
        icon: ImageVector,
        title: Int,
        description: Int?,
        val steps: Int,
        val valueRange: ClosedFloatingPointRange<Float>,
        val value: () -> Int,
        val preview: @Composable (value: Int) -> Unit,
    ) : Setting(id, icon, title, description) {

        fun change(size: Int): SettingChange {
            return Change(size)
        }

        data class Change(val size: Int) : SettingChange
    }

    class Switch(
        id: String,
        icon: ImageVector,
        title: Int,
        description: Int?,
        val isEnabled: Boolean,
        val preview: (@Composable () -> Unit)?,
    ) : Setting(id, icon, title, description) {

        fun change(isEnabled: Boolean): SettingChange {
            return Change(isEnabled)
        }

        data class Change(val isEnabled: Boolean) : SettingChange
    }
}