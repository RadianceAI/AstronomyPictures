package by.radiance.space.pictures.presenter.ui.settings.setting

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Start
import androidx.compose.runtime.Composable
import androidx.compose.runtime.traceEventEnd
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.radiance.space.pictures.presenter.R
import by.radiance.space.pictures.presenter.ui.settings.Title
import by.radiance.space.pictures.presenter.utils.contentDescription
import by.radiance.space.pictures.presenter.ui.settings.model.Setting
import by.radiance.space.pictures.presenter.ui.theme.AstronomyPicturesTheme
import by.radiance.space.pictures.presenter.ui.utils.ListItem
import by.radiance.space.pictures.presenter.utils.stringResource

@Composable
fun PickerSetting(
    modifier: Modifier = Modifier,
    setting: Setting.Picker<Any>,
    onOptionSelected: (Setting.SettingChange) -> Unit,
) {
    Column (
        modifier = modifier,
    ) {
        Title(
            setting = setting,
        )

        Row(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(),
        ) {
            setting.options.forEachIndexed { index, option ->
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.CenterVertically)
                        .weight(1f)
                        .clip(shape = MaterialTheme.shapes.medium.copy(all = CornerSize(8.dp)))
                        .clickable {
                            onOptionSelected(setting.change(index))
                        },
                ) {
                    setting.preview(option)
                }
            }
        }
    }
}

@Preview
@Composable
fun PickerPreview() {
    AstronomyPicturesTheme {
        PickerSetting(
            modifier = Modifier,
            setting = Setting.Picker<Any>(
                id = "",
                icon = Icons.Filled.Star,
                title = R.string.app_name,
                description = null,
                options = listOf("option1", "option2", "option3"),
                preview = {
                    Text(it as String)
                }
            ),
            onOptionSelected = {}
        )
    }
}