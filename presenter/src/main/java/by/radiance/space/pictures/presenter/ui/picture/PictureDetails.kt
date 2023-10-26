package by.radiance.space.pictures.presenter.ui.picture

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.graphics.drawable.Drawable
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.radiance.space.pictures.presenter.R
import by.radiance.space.pictures.presenter.ui.theme.AstronomyPicturesTheme
import by.radiance.space.pictures.presenter.ui.theme.CardGray
import by.radiance.space.pictures.presenter.ui.utils.*
import by.radiance.space.pictures.domain.entity.Id
import by.radiance.space.pictures.domain.entity.Image
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.presenter.state.PictureUiState
import by.radiance.space.pictures.domain.utils.DateHelper
import coil.compose.SubcomposeAsyncImage
import java.util.*


@Composable
fun PictureDetails(
    heightWindowSize: WindowSize,
    modifier: Modifier = Modifier,
    pictureUiState: PictureUiState,
    progress: Boolean,
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
        modifier = modifier,
        bottomBar = {
            AnimatedVisibility(visible = heightWindowSize != WindowSize.Compact) {
                BottomNavigation(
                    modifier = Modifier
                        .padding(5.dp)
                        .clip(RoundedCornerShape(5.dp, 5.dp, 16.dp, 16.dp)),
                    backgroundColor = CardGray,
                ) {
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
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            Row {
                if (heightWindowSize == WindowSize.Compact) {
                    Column(
                        modifier = Modifier
                            .padding(5.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .background(CardGray),
                    ) {
                        ColumnBottomNavigationItem(icon = Icons.Default.Crop, onClick = {
                            cropState = cropList[
                                    (cropList.indexOf(cropState) + 1)
                                        .takeIf { index -> index != cropList.size }
                                        ?: 0
                            ]
                        })
                        ColumnBottomNavigationItem(icon = Icons.Default.Wallpaper, onClick = {
                            drawable?.let { wallpaper ->
                                openDialog = true
                            }
                        })
                        ColumnBottomNavigationItem(icon = Icons.Default.Share, onClick = {
                            drawable?.let { drawableToShare ->
                                onShare(drawableToShare)
                            }
                        })
                        ColumnBottomNavigationItem(icon = Icons.Default.Info, onClick = {
                            details = !details
                        })
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    when (pictureUiState) {
                        is PictureUiState.Success -> {
                            SubcomposeAsyncImage(
                                modifier = Modifier
                                    .horizontalScroll(rememberScrollState())
                                    .fillMaxSize(),
                                model = pictureUiState.picture.source.huge,
                                contentDescription = pictureUiState.picture.explanation,
                                loading = { LoadingCard() },
                                contentScale = cropState,
                                onSuccess = { painterState ->
                                    drawable = painterState.result.drawable
                                }
                            )
                        }
                        is PictureUiState.Loading -> {
                            LoadingCard(cornerSize = CornerSize(0.dp))
                        }
                        is PictureUiState.Error -> {
                            ErrorCard(error = "Something went wrong", cornerSize = CornerSize(0.dp))
                        }
                    }
                    if (details) {
                        if (pictureUiState is PictureUiState.Success) {
                            pictureUiState.picture.let { picture ->
                                Detail(
                                    modifier = Modifier
                                        .background(CardGray.copy(alpha = 0.6f))
                                        .padding(10.dp),
                                    picture = picture
                                )
                            }
                        }
                    }
                    if (progress) {
                        Box(modifier = Modifier
                            .fillMaxSize()
                            .background(CardGray.copy(0.5f))) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }
                    }
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Detail(
    modifier: Modifier = Modifier,
    picture: Picture,
) {
    val context = LocalContext.current
    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = picture.title ?: "",
            style = MaterialTheme.typography.h6,
            modifier = Modifier
                .combinedClickable(
                    onClick = { },
                    onLongClick = {
                        copyToClipboard(context, picture.title)
                    },
                )
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
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .combinedClickable(
                        onClick = { },
                        onLongClick = {
                            copyToClipboard(context, picture.copyright)
                        },
                    )
            )
        }
        Text(
            text = stringResource(id = R.string.explanation),
            style = MaterialTheme.typography.body2
        )
        Text(
            text = picture.explanation ?: "",
            style = MaterialTheme.typography.body2,
            modifier = Modifier
                .combinedClickable(
                    onClick = { },
                    onLongClick = {
                        copyToClipboard(context, picture.explanation)
                    },
                )
        )
    }
}

private fun copyToClipboard(
    context: Context,
    text: String?
) {
    val clipboard = context.getSystemService(
        CLIPBOARD_SERVICE
    ) as ClipboardManager?
    val clip: ClipData = ClipData.newPlainText("", text)
    clipboard?.setPrimaryClip(clip)

    Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT).show()
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