package by.radiance.space.pictures.presenter.ui.picture

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.graphics.drawable.Drawable
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.material.icons.filled.Wallpaper
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import by.radiance.space.pictures.domain.presenter.state.PictureUiState
import by.radiance.space.pictures.domain.utils.DateUtil
import by.radiance.space.pictures.presenter.R
import by.radiance.space.pictures.presenter.ui.error.ErrorCard
import by.radiance.space.pictures.presenter.ui.theme.CardGray
import by.radiance.space.pictures.presenter.ui.utils.ColumnBottomNavigationItem
import by.radiance.space.pictures.presenter.ui.utils.HorizontalScaffold
import by.radiance.space.pictures.presenter.ui.utils.WindowSize
import coil.compose.SubcomposeAsyncImage

@Composable
fun PictureDetailsView(
    modifier: Modifier = Modifier,
    pictureUiState: PictureUiState,
    progress: Boolean,
    heightWindowSize: WindowSize,
    onShare: (Drawable) -> Unit,
    onSystemWallpaper: (Drawable) -> Unit,
    onLockScreenWallpaper: (Drawable) -> Unit,
    onAllWallpaper: (Drawable) -> Unit,
) {
    var isDetailsVisible by remember { mutableStateOf(false) }
    val (drawable, setDrawable) = remember { mutableStateOf<Drawable?>(null) }
    var isWallPaperDialogVisible by remember { mutableStateOf(false)  }

    val onOpenDetailsClicked = {
        isDetailsVisible = !isDetailsVisible
    }
    val onOpenWallpaperDialogClicked = {
        isWallPaperDialogVisible = true
    }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            AnimatedVisibility(visible = heightWindowSize != WindowSize.Compact) {
                BottomBar(
                    drawable = drawable,
                    onShare = onShare,
                    onOpenDetailsClicked = onOpenDetailsClicked,
                    onOpenWallpaperDialogClicked = onOpenWallpaperDialogClicked,
                )
            }
        },
        content = { innerPaddings ->
            Content(
                innerPaddings = innerPaddings,
                heightWindowSize = heightWindowSize,
                drawable = drawable,
                onShare = onShare,
                pictureUiState = pictureUiState,
                onOpenDetailsClicked = onOpenDetailsClicked,
                onOpenWallpaperDialogClicked = onOpenWallpaperDialogClicked,
                onDrawableReceived = setDrawable,
            )

            AnimatedVisibility(visible = isDetailsVisible) {
                Detail(
                    modifier = Modifier
                        .background(CardGray.copy(alpha = 0.6f))
                        .padding(10.dp),
                    pictureUiState = pictureUiState
                )
            }

            AnimatedVisibility(visible = progress) {
                Progress(modifier = Modifier.background(CardGray.copy(0.5f)))
            }

            AnimatedVisibility(visible = isWallPaperDialogVisible) {
                WallpaperDialog(
                    onDismiss = { isWallPaperDialogVisible = false },
                    onSystem = {
                        drawable?.let { wallpaper ->
                            onSystemWallpaper(wallpaper)
                        }
                        isWallPaperDialogVisible = false
                    },
                    onLockScreen = {
                        drawable?.let { wallpaper ->
                            onLockScreenWallpaper(wallpaper)
                        }
                        isWallPaperDialogVisible = false
                    },
                    onAll = {
                        drawable?.let { wallpaper ->
                            onAllWallpaper(wallpaper)
                        }
                        isWallPaperDialogVisible = false
                    }
                )
            }
        }
    )
}

@Composable
private fun Content(
    innerPaddings: PaddingValues,
    heightWindowSize: WindowSize,
    drawable: Drawable?,
    onShare: (Drawable) -> Unit,
    pictureUiState: PictureUiState,
    onOpenWallpaperDialogClicked: () -> Unit,
    onOpenDetailsClicked: () -> Unit,
    onDrawableReceived: (Drawable) -> Unit,
) {
    HorizontalScaffold(
        modifier = Modifier.padding(innerPaddings),
        startBar = {
            AnimatedVisibility(visible = heightWindowSize == WindowSize.Compact) {
                HorizontalBar(
                    drawable = drawable,
                    onShare = onShare,
                    onOpenDetailsClicked = onOpenDetailsClicked,
                    onOpenWallpaperDialogClicked = onOpenWallpaperDialogClicked,
                )
            }
        },
        content = {
            when (pictureUiState) {
                is PictureUiState.Success -> {
                    SubcomposeAsyncImage(
                        modifier = Modifier
                            .horizontalScroll(rememberScrollState())
                            .fillMaxSize(),
                        model = pictureUiState.picture.source.huge,
                        contentDescription = pictureUiState.picture.explanation,
                        loading = {
                            Progress()
                        },
                        contentScale = ContentScale.FillHeight,
                        onSuccess = { painterState ->
                            onDrawableReceived(painterState.result.drawable)
                        }
                    )
                }

                is PictureUiState.Loading -> {
                    Progress()
                }

                is PictureUiState.Error -> {
                    ErrorCard(error = "Something went wrong")
                }
            }
        }
    )
}

@Composable
private fun HorizontalBar(
    drawable: Drawable?,
    onShare: (Drawable) -> Unit,
    onOpenWallpaperDialogClicked: () -> Unit,
    onOpenDetailsClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(CardGray),
    ) {
        ColumnBottomNavigationItem(
            icon = Icons.Default.Wallpaper,
            onClick = {
                drawable?.let {
                    onOpenWallpaperDialogClicked()
                }
            },
        )
        ColumnBottomNavigationItem(
            icon = Icons.Default.Share,
            onClick = {
                drawable?.let { drawableToShare ->
                    onShare(drawableToShare)
                }
            },
        )
        ColumnBottomNavigationItem(
            icon = Icons.Default.Info,
            onClick = {
                onOpenDetailsClicked()
            },
        )
    }
}


@Composable
private fun BottomBar(
    drawable: Drawable?,
    onShare: (Drawable) -> Unit,
    onOpenWallpaperDialogClicked: () -> Unit,
    onOpenDetailsClicked: () -> Unit,
) {
    BottomNavigation(
        modifier = Modifier,
        backgroundColor = CardGray,
    ) {
        BottomIcon(icon = Icons.Default.Wallpaper) {
            drawable?.let {
                onOpenWallpaperDialogClicked()
            }
        }
        BottomIcon(icon = Icons.Default.Share) {
            drawable?.let { drawableToShare ->
                onShare(drawableToShare)
            }
        }
        BottomIcon(icon = Icons.Default.Info) {
            onOpenDetailsClicked()
        }
    }
}

@Composable
private fun Progress(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
        )
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
    pictureUiState: PictureUiState,
) {
    if (pictureUiState !is PictureUiState.Success) return

    val context = LocalContext.current
    val picture = pictureUiState.picture

    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            modifier = Modifier
                .onLongClick {
                    context.copyToClipboard(picture.title)
                },
            text = picture.title ?: "",
            style = MaterialTheme.typography.h5,
        )
        Text(
            text = DateUtil.getDate(DateUtil.parseId(picture.id.date)),
            style = MaterialTheme.typography.body2
        )
        Row {
            Text(
                text = stringResource(id = R.string.copyright),
                style = MaterialTheme.typography.body2
            )
            Text(
                modifier = Modifier
                    .onLongClick {
                        context.copyToClipboard(picture.copyright)
                    },
                text = picture.copyright ?: "",
                style = MaterialTheme.typography.body2,
            )
        }
        Text(
            text = stringResource(id = R.string.explanation),
            style = MaterialTheme.typography.body2
        )
        Text(
            modifier = Modifier
                .onLongClick {
                    context.copyToClipboard(picture.explanation)
                },
            text = picture.explanation ?: "",
            style = MaterialTheme.typography.body2,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
private fun Modifier.onLongClick(action: () -> Unit) = apply {
    this.combinedClickable(
            onClick = { },
            onLongClick = action,
        )
}

private fun Context.copyToClipboard(
    text: String?
) {
    val clipboard = getSystemService(
        CLIPBOARD_SERVICE
    ) as ClipboardManager?
    val clip: ClipData = ClipData.newPlainText("", text)
    clipboard?.setPrimaryClip(clip)

    Toast.makeText(this, "Copied to clipboard", Toast.LENGTH_SHORT).show()
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
