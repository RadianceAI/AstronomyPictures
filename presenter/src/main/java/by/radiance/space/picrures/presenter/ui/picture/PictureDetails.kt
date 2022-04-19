package by.radiance.space.picrures.presenter.ui.picture

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Crop
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Wallpaper
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import by.radiance.space.picrures.presenter.ui.theme.CardGray
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.presenter.state.PictureUiState
import coil.compose.SubcomposeAsyncImage

@Composable
fun PictureDetails(
    modifier: Modifier = Modifier,
    picture: PictureUiState,
    onLike: (Picture) -> Unit,
) {
    val cropList = listOf(ContentScale.Crop, ContentScale.FillBounds, ContentScale.FillHeight, ContentScale.FillWidth, ContentScale.Fit, ContentScale.Inside)
    var cropState by remember { mutableStateOf(cropList[0]) }
    var details by remember { mutableStateOf(false) }

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
                        .fillMaxSize()
                        .clickable { details = !details },
                    model = (picture as PictureUiState.Success).picture?.source?.huge,
                    contentDescription = picture.picture?.explanation,
                    loading = { CircularProgressIndicator() },
                    contentScale = cropState
                )

                if (details) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .background(Color.LightGray.copy(alpha = 0.8f))
                            .clickable { details = !details }
                            .padding(8.dp),
                        text = picture.picture?.explanation?:"",
                        style = MaterialTheme.typography.body1,
                    )
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

                }
                BottomIcon(icon = Icons.Default.Share) {

                }
                BottomIcon(
                    icon = if ((picture as PictureUiState.Success).picture?.saveDate == null) Icons.Outlined.FavoriteBorder else Icons.Outlined.Favorite,
                ) {

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
