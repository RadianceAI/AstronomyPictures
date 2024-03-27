package by.radiance.space.pictures.presenter.ui.settings.setting

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.radiance.space.pictures.presenter.ui.settings.Title
import by.radiance.space.pictures.presenter.ui.settings.model.Setting
import by.radiance.space.pictures.presenter.ui.theme.AstronomyPicturesTheme
import by.radiance.space.pictures.presenter.utils.picker

@Composable
fun PickerSetting(
    modifier: Modifier = Modifier,
    setting: Setting.Picker<Any>,
    onOptionSelected: (Setting.SettingChange) -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        Title(setting = setting)

        Row(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(),
        ) {
            setting.options.forEachIndexed { index, option ->
                Card(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.CenterVertically)
                        .weight(1f),
                    shape = MaterialTheme.shapes.medium,
                    border = BorderStroke(
                        width = 2.dp,
                        color = if (setting.isSelected(option)) MaterialTheme.colors.primary else Color.LightGray
                    ),
                ) {
                    Box(modifier = Modifier.clickable { onOptionSelected(setting.change(index)) }) {
                        setting.preview(option)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PickerPreview() {
    AstronomyPicturesTheme {
        PickerSetting(
            modifier = Modifier,
            setting = picker,
            onOptionSelected = {},
        )
    }
}