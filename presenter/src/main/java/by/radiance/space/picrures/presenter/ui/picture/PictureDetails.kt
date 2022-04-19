package by.radiance.space.picrures.presenter.ui.picture

import android.app.WallpaperManager
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.radiance.space.picrures.presenter.R
import by.radiance.space.picrures.presenter.ui.theme.AstronomyPicturesTheme
import by.radiance.space.picrures.presenter.ui.theme.CardGray
import by.radiance.space.pictures.domain.entity.Id
import by.radiance.space.pictures.domain.entity.Image
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.presenter.state.PictureUiState
import by.radiance.space.pictures.domain.utils.DateHelper
import coil.compose.SubcomposeAsyncImage
import kotlinx.coroutines.async
import java.util.*


@Composable
fun PictureDetails(
    modifier: Modifier = Modifier,
    picture: PictureUiState,
    onShare: (Drawable) -> Unit,
    onSystemWallpaper: (Drawable) -> Unit,
    onLockScreenWallpaper: (Drawable) -> Unit,
    onAllWallpaper: (Drawable) -> Unit,
) {
    val cropList = listOf(ContentScale.Crop, ContentScale.FillBounds, ContentScale.FillHeight, ContentScale.FillWidth, ContentScale.Fit, ContentScale.Inside)
    var cropState by remember { mutableStateOf(cropList[0]) }
    var details by remember { mutableStateOf(false) }
    var drawable by remember { mutableStateOf<Drawable?>(null) }
    var openDialog by remember { mutableStateOf(false)  }

    Scaffold(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {
                SubcomposeAsyncImage(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                        .fillMaxSize(),
                    model = (picture as PictureUiState.Success).picture?.source?.huge,
                    contentDescription = picture.picture?.explanation,
                    loading = { CircularProgressIndicator() },
                    contentScale = cropState,
                    onSuccess = { painterState ->
                        drawable = painterState.result.drawable
                    }
                )

                if (details) {
                    picture.picture?.let { picture ->
                        Detail(
                            modifier = Modifier
                                .background(CardGray.copy(alpha = 0.6f))
                                .padding(10.dp),
                            picture = picture
                        )
                    }
                }
            }

            BottomNavigation(
                modifier = Modifier
                    .padding(5.dp)
                    .clip(RoundedCornerShape(5.dp, 5.dp, 16.dp, 16.dp)),
                backgroundColor = CardGray,
            ){
                BottomIcon(icon = Icons.Default.Crop) {
                    cropState = cropList[
                            (cropList.indexOf(cropState) + 1)
                                .takeIf { index -> index != cropList.size }
                                ?: 0
                    ]
                }
                BottomIcon(icon = Icons.Default.Wallpaper) {
                    drawable?.let { wallpaper ->
                        openDialog = true
                    }
                }
                BottomIcon(icon = Icons.Default.Share) {
                    drawable?.let { drawableToShare ->
                        onShare(drawableToShare)
                    }
                }
                BottomIcon(icon = Icons.Default.Info) {
                    details = !details
                }
            }
        }

        AnimatedVisibility(visible = openDialog) {
            WallpaperDialog(
                onDismiss = { openDialog = false },
                onSystem = {
                    drawable?.let { wallpaper ->
                        onSystemWallpaper(wallpaper)
                    }
                    openDialog = false
                },
                onLockScreen = {
                    drawable?.let { wallpaper ->
                        onLockScreenWallpaper(wallpaper)
                    }
                    openDialog = false
                },
                onAll = {
                    drawable?.let { wallpaper ->
                        onAllWallpaper(wallpaper)
                    }
                    openDialog = false
                }
            )
        }
    }
}


@Composable
fun RowScope.BottomIcon(
    icon: ImageVector,
    onClick: () -> Unit,
) {
    BottomNavigationItem(
        icon = {
            Icon(
                icon,
                null
            )
        },
        selected = true,
        onClick = {
            onClick.invoke()
        }
    )
}

@Composable
fun Detail(
    modifier: Modifier = Modifier,
    picture: Picture,
) {
    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = picture.title ?: "",
            style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.SemiBold),
        )
        Text(
            text = DateHelper.getDate(picture.id.date),
            style = MaterialTheme.typography.body2
        )
        Row {
            Text(
                text = stringResource(id = R.string.copyright),
                style = MaterialTheme.typography.body2
            )
            Text(
                text = picture.copyright ?: "",
                style = MaterialTheme.typography.body2
            )
        }
        Text(
            text = stringResource(id = R.string.explanation),
            style = MaterialTheme.typography.body2
        )
        Text(
            text = picture.explanation ?: "",
            style = MaterialTheme.typography.body2
        )
    }
}

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
                DialogButton(icon = Icons.Filled.Home, text = "System", onClick = onSystem)
                DialogButton(icon = Icons.Filled.Lock, text = "Lock screen", onClick = onLockScreen)
                DialogButton(icon = Icons.Filled.Smartphone, text = "System & Lock screen", onClick = onAll)
            }
        }
    )
}

@Composable
fun DialogButton(icon: ImageVector, text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Icon(
            icon,
            null,
            Modifier
                .padding(10.dp)
                .align(Alignment.CenterVertically)
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically),
            text = text
        )
    }
}

@Preview
@Composable
fun DetailsPreview() {
    AstronomyPicturesTheme {
        Detail(picture = Picture(
            id = Id(Date()),
            title = "Title",
            explanation = "Explanation",
            copyright = "cop",
            source = Image(light = "", huge = ""),
            isSaved = true,
            saveDate = Date()
        ))
    }
}