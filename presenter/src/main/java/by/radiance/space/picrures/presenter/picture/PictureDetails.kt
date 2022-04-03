package by.radiance.space.picrures.presenter.picture

import android.graphics.drawable.Drawable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Crop
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Wallpaper
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.radiance.space.pictures.local.entity.AstronomyPicture
import by.radiance.space.pictures.local.entity.SourceType
import coil.compose.SubcomposeAsyncImage
import java.sql.Date
import java.util.*

@Composable
fun PictureDetails(
    modifier: Modifier = Modifier,
    picture: AstronomyPicture,
    onLike: (AstronomyPicture) -> Unit,
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
                    model = picture.hsrc,
                    contentDescription = picture.explanation,
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
                        text = picture.explanation?:"",
                        style = MaterialTheme.typography.body1,
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray.copy(alpha = 0.8f))
            ) {
                IconButton(
                    modifier = Modifier
                        .weight(1f),
                    onClick = {
                        cropState = cropList[
                                (cropList.indexOf(cropState) + 1)
                                    .takeIf { index -> index != cropList.size }
                                    ?: 0
                        ]
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Crop,
                        contentDescription = null,
                        modifier = modifier
                            .padding(8.dp)
                    )
                }
                IconButton(
                    modifier = Modifier
                        .weight(1f),
                    onClick = { }
                ) {
                    Icon(
                        imageVector = Icons.Default.Wallpaper,
                        contentDescription = null,
                        modifier = modifier
                            .padding(8.dp)
                    )
                }
                IconButton(
                    modifier = Modifier
                        .weight(1f),
                    onClick = { }
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = null,
                        modifier = modifier
                            .padding(8.dp)
                    )
                }
                IconButton(
                    modifier = Modifier
                        .weight(1f),
                    onClick = {  }
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
}

@Preview
@Composable
fun PictureDetailsPreview() {
    PictureDetails(
        picture = AstronomyPicture(
            id = Date(Date().time),
            title = "Title",
            explanation = "Our Earth is not at rest.  The Earth moves around the Sun.  The Sun orbits the center of the Milky Way Galaxy.  The Milky Way Galaxy orbits in the Local Group of Galaxies.  The Local Group falls toward the Virgo Cluster of Galaxies.  But these speeds are less than the speed that all of these objects together move relative to the cosmic microwave background radiation (CMBR).  In the featured all-sky map from the COBE satellite in 1993, microwave light in the Earth's direction of motion appears blueshifted and hence hotter, while microwave light on the opposite side of the sky is redshifted and colder.  The map indicates that the Local Group moves at about 600 kilometers per second relative to this primordial radiation.  This high speed was initially unexpected and its magnitude is still unexplained.  Why are we moving so fast?  What is out there?",
            copyright = "copyright",
            type = SourceType.Image,
            src = "",
            hsrc = 	"https://apod.nasa.gov/apod/image/2204/CmbDipole_cobe_960.jpg",
            saveDate = null,
        ),
        onLike = {}
    )
}