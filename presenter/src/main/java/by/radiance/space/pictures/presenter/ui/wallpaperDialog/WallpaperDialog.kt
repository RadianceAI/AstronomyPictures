package by.radiance.space.pictures.presenter.ui.wallpaperDialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.radiance.space.pictures.presenter.R
import by.radiance.space.pictures.presenter.ui.theme.AstronomyPicturesTheme

@Composable
fun WallpaperDialog(
    onDismiss: () -> Unit,
    onSystem: () -> Unit,
    onLockScreen: () -> Unit,
    onAll: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        buttons = {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .wrapContentWidth()
            ) {
                DialogButton(
                    icon = Icons.Filled.Home,
                    text = stringResource(R.string.system),
                    onClick = onSystem,
                )
                DialogButton(
                    icon = Icons.Filled.Lock,
                    text = stringResource(R.string.lock_screen),
                    onClick = onLockScreen,
                )
                DialogButton(
                    icon = Icons.Filled.Smartphone,
                    text = stringResource(R.string.system_and_lock_screen),
                    onClick = onAll,
                )
            }
        }
    )
}

@Composable
fun DialogButton(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
    ) {
        Icon(
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.CenterVertically),
            imageVector = icon,
            contentDescription = text,
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically),
            text = text,
        )
    }
}

@Preview
@Composable
fun WallpaperDialogPreview() {
    AstronomyPicturesTheme {
        WallpaperDialog(
            onDismiss = {},
            onSystem = {},
            onLockScreen = {},
            onAll = {},
        )
    }
}
