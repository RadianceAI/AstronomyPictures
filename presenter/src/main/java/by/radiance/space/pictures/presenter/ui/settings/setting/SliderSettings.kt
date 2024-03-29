package by.radiance.space.pictures.presenter.ui.settings.setting

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.radiance.space.pictures.presenter.ui.settings.Title
import by.radiance.space.pictures.presenter.ui.settings.model.Setting
import by.radiance.space.pictures.presenter.ui.theme.AstronomyPicturesTheme
import by.radiance.space.pictures.presenter.utils.slider

@Composable
fun SliderSettings(
    modifier: Modifier = Modifier,
    setting: Setting.Slider,
    onOptionChanged: (Setting.SettingChange) -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        val (slider, setSlider) = remember { mutableFloatStateOf(setting.value().toFloat()) }

        Title(setting = setting)

        Box(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
            setting.preview(slider.toInt())
        }

        Slider(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            value = slider,
            onValueChange = setSlider,
            onValueChangeFinished = {
                onOptionChanged(setting.change(slider.toInt()))
            },
            steps = setting.steps,
            valueRange = setting.valueRange,
        )
    }
}

@Preview
@Composable
private fun Preview() {
    AstronomyPicturesTheme {
        SliderSettings(
            setting = slider,
            onOptionChanged = {}
        )
    }
}