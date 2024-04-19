package by.radiance.space.pictures.presenter.ui.details

import android.graphics.drawable.Drawable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Wallpaper
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import by.radiance.space.pictures.domain.presenter.state.PictureUiState
import by.radiance.space.pictures.presenter.R
import by.radiance.space.pictures.presenter.ui.actionMenu.Action
import by.radiance.space.pictures.presenter.ui.actionMenu.ActionBar
import by.radiance.space.pictures.presenter.ui.actionMenu.ActionBarOrientation
import by.radiance.space.pictures.presenter.ui.progress.Progress
import by.radiance.space.pictures.presenter.ui.theme.CardGray
import by.radiance.space.pictures.presenter.ui.utils.HorizontalScaffold
import by.radiance.space.pictures.presenter.ui.utils.WindowSize
import by.radiance.space.pictures.presenter.ui.wallpaperDialog.WallpaperDialog

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

    val actions = listOf(
        Action(
            icon = Icons.Default.Wallpaper,
            contentDescription = stringResource(R.string.set_wallpaper),
            onClick = {
                drawable?.let {
                    onOpenWallpaperDialogClicked()
                }
            },
        ),
        Action(
            icon = Icons.Default.Share,
            contentDescription = stringResource(R.string.share_image),
            onClick = {
                drawable?.let(onShare)
            },
        ),
        Action(
            icon = Icons.Default.Info,
            contentDescription = stringResource(R.string.open_image_description),
            onClick = onOpenDetailsClicked,
        ),
    )

    Scaffold(
        modifier = modifier,
        bottomBar = {
            AnimatedVisibility(visible = heightWindowSize != WindowSize.Compact) {
                ActionBar(
                    modifier = Modifier,
                    orientation = ActionBarOrientation.Horizontal,
                    actions = actions,
                )
            }
        },
        content = { innerPaddings ->
            Content(
                innerPaddings = innerPaddings,
                heightWindowSize = heightWindowSize,
                isDetailsVisible = isDetailsVisible,
                progress = progress,
                pictureUiState = pictureUiState,
                onDrawableReceived = setDrawable,
                actions = actions,
            )

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
    pictureUiState: PictureUiState,
    isDetailsVisible: Boolean,
    progress: Boolean,
    actions: List<Action>,
    onDrawableReceived: (Drawable) -> Unit,
) {
    HorizontalScaffold(
        modifier = Modifier,
        startBar = {
            AnimatedVisibility(visible = heightWindowSize == WindowSize.Compact) {
                ActionBar(
                    modifier = Modifier,
                    orientation = ActionBarOrientation.Vertical,
                    actions = actions,
                )
            }
        },
        content = {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    heightWindowSize = heightWindowSize,
                    pictureUiState = pictureUiState,
                    onDrawableReceived = onDrawableReceived,
                )

                AnimatedVisibility(visible = isDetailsVisible) {
                    PictureDescription(
                        modifier = Modifier
                            .background(CardGray.copy(alpha = 0.6f))
                            .padding(10.dp),
                        pictureUiState = pictureUiState
                    )
                }

                AnimatedVisibility(visible = progress) {
                    Progress(modifier = Modifier.fillMaxSize().background(CardGray.copy(0.5f)))
                }
            }
        }
    )
}