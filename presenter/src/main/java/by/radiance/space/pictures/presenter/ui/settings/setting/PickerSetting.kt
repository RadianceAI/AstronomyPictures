package by.radiance.space.pictures.presenter.ui.settings.setting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import by.radiance.space.pictures.presenter.ui.settings.Title
import by.radiance.space.pictures.presenter.ui.settings.contentDescription
import by.radiance.space.pictures.presenter.utils.stringResource
import by.radiance.space.pictures.presenter.viewModel.settings.setting.Setting

@Composable
fun PickerSetting(
    setting: Setting.Picker,
    onOptionSelected: (Int) -> Unit,
) {
    Column (
        modifier = Modifier
            .padding(8.dp),
    ) {
        Title(
            icon = setting.icon,
            title = stringResource(setting.title),
            description = setting.description.stringResource(),
            contentDescription = setting.contentDescription(),
        )

        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(),
        ) {
            setting.options.forEachIndexed { index, option ->
                Column(
                    Modifier
                        .align(Alignment.CenterVertically)
                        .weight(1f)
                        .padding(8.dp)
                        .clickable {
                            onOptionSelected(index)
                        }
                ) {
                    if (option.title != null) {
                        Text(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally),
                            text = stringResource(option.title),
                            style = MaterialTheme.typography.h1,
                        )
                    }
                    if (option.description != null) {
                        Text(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally),
                            text = stringResource(option.description),
                            style = MaterialTheme.typography.caption,
                        )
                    }
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                    ) {
                        option.view(setting.selectedItemIndex == index)
                    }
                }
            }
        }
    }
}