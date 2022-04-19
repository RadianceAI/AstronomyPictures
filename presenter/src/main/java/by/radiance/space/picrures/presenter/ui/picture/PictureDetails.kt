package by.radiance.space.picrures.presenter.ui.picture

import android.app.WallpaperManager
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Crop
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Wallpaper
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import java.util.*


@Composable
fun PictureDetails(
    modifier: Modifier = Modifier,
    picture: PictureUiState,
    onShare: (Drawable) -> Unit,
    onSetWallpaper: (Drawable, Int) -> Unit,
) {
    val cropList = listOf(ContentScale.Crop, ContentScale.FillBounds, ContentScale.FillHeight, ContentScale.FillWidth, ContentScale.Fit, ContentScale.Inside)
    var cropState by remember { mutableStateOf(cropList[0]) }
    var details by remember { mutableStateOf(false) }
    var drawable by remember { mutableStateOf<Drawable?>(null) }
    val context = LocalContext.current

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
                        //todo show dialog
                        //start loading
                        val wallpaperManager = WallpaperManager.getInstance(context)

                        val bitmap = (wallpaper as BitmapDrawable).bitmap!!
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            wallpaperManager.setBitmap(
                                bitmap,
                                null,
                                true,
                                WallpaperManager.FLAG_SYSTEM
                            )
                        } else {
                            wallpaperManager.setBitmap(bitmap)
                        }
                    }
                }
                BottomIcon(icon = Icons.Default.Share) {
                    drawable?.let { drawableToShare ->
                        //todo start loading
                        onShare(drawableToShare)
                    }
                }
                BottomIcon(icon = Icons.Default.Info) {
                    details = !details
                }
            }
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