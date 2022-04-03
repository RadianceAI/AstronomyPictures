package by.radiance.space.picrures.presenter.picture

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.radiance.space.pictures.local.entity.AstronomyPicture
import by.radiance.space.pictures.local.entity.SourceType
import coil.compose.SubcomposeAsyncImage
import java.sql.Date

@Composable
fun Picture(
    modifier: Modifier = Modifier,
    picture: AstronomyPicture,
    onClick: (AstronomyPicture) -> Unit,
    onLike: (AstronomyPicture) -> Unit
) {
    Card(
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .clickable { onClick(picture) }
    ) {
        Box() {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                model = picture.hsrc,
                contentDescription = picture.explanation,
                loading = { CircularProgressIndicator() },
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray.copy(alpha = 0.8f))
                    .align(Alignment.BottomCenter)
                    .padding(8.dp)
                ,
                text = picture.title ?: "",
                style = MaterialTheme.typography.h5,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            IconButton(
                modifier = Modifier
                    .align(Alignment.TopEnd),
                onClick = { onLike(picture) }
            ) {
                Icon(
                    imageVector = if (picture.saveDate == null) Icons.Outlined.FavoriteBorder
                                        else Icons.Outlined.Favorite,
                    contentDescription = null,
                    modifier = modifier
                        .padding(8.dp)
                )
            }
        }
    }
}

@Composable
@Preview
fun PicturePreview() {
    Picture(
        modifier = Modifier,
        picture = AstronomyPicture(
            id = Date(java.util.Date().time),
            title = "Title",
            explanation = null,
            copyright = "copyright",
            type = SourceType.Image,
            src = "https://apod.nasa.gov/apod/image/2204/CmbDipole_cobe_960.jpg",
            hsrc = 	"https://apod.nasa.gov/apod/image/2204/CmbDipole_cobe_960.jpg",
            saveDate = null,
        ),
        onClick = {},
        onLike = {},
    )
}