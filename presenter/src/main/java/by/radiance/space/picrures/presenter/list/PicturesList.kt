package by.radiance.space.picrures.presenter.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.radiance.space.picrures.presenter.picture.Picture
import by.radiance.space.pictures.local.entity.AstronomyPicture
import by.radiance.space.pictures.local.entity.SourceType
import java.sql.Date
import androidx.compose.foundation.lazy.items
import java.util.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PictureList(
    modifier: Modifier = Modifier,
    todayPicture: AstronomyPicture?,
    randomPicture: AstronomyPicture?,
    savedList: List<AstronomyPicture>,
    onClick: (AstronomyPicture) -> Unit,
    onLike: (AstronomyPicture) -> Unit,
) {
    Scaffold(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(it)
        ) {
            TodayPictures(
                todayPicture = todayPicture,
                randomPicture = randomPicture,
                onClick = onClick,
                onLike = onLike,
            )

            LazyVerticalGrid(
                cells = GridCells.Fixed(3),
                content = {
                    items(savedList) {
                        Picture(
                            modifier = Modifier
                                .height(150.dp)
                                .padding(4.dp)
                            ,
                            picture = it,
                            onClick = onClick,
                            onLike = onLike
                        )
                    }
                })
        }
    }
}

@Composable
fun TodayPictures(
    todayPicture: AstronomyPicture?,
    randomPicture: AstronomyPicture?,
    onClick: (AstronomyPicture) -> Unit,
    onLike: (AstronomyPicture) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(4.dp)
        ) {
            if (todayPicture != null) {
                Picture(
                    modifier = Modifier.height(250.dp),
                    picture = todayPicture,
                    onClick = onClick,
                    onLike = onLike
                )
            } else {
                CircularProgressIndicator()
            }
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(4.dp)
        ) {
            if (randomPicture != null) {
                Picture(
                    modifier = Modifier.height(250.dp),
                    picture = randomPicture,
                    onClick = onClick,
                    onLike = onClick
                )
            } else {
                CircularProgressIndicator()
            }
        }
    }
}


@Preview
@Composable
fun PictureListPreview() {
    PictureList(
        todayPicture = AstronomyPicture(
            id = Date(Date().time),
            title = "CMB Dipole: Speeding Through the Universe",
            explanation = null,
            copyright = "copyright",
            type = SourceType.Image,
            src = "https://apod.nasa.gov/apod/image/2204/CmbDipole_cobe_960.jpg",
            hsrc = 	"https://apod.nasa.gov/apod/image/2204/CmbDipole_cobe_960.jpg",
            saveDate = null,
        ),
        randomPicture = AstronomyPicture(
            id = Date(Date().time),
            title = "Star Clusters Young and Old",
            explanation = null,
            copyright = "copyright",
            type = SourceType.Image,
            src = "https://apod.nasa.gov/apod/image/0609/m46m47_hetlage_big.jpg",
            hsrc = 	"https://apod.nasa.gov/apod/image/0609/m46m47_hetlage_big.jpg",
            saveDate = null,
        ),
        savedList = listOf(
            AstronomyPicture(
                id = Date(Date().time),
                title = "Star Clusters Young and Old",
                explanation = null,
                copyright = "copyright",
                type = SourceType.Image,
                src = "https://apod.nasa.gov/apod/image/0609/m46m47_hetlage_big.jpg",
                hsrc = 	"https://apod.nasa.gov/apod/image/0609/m46m47_hetlage_big.jpg",
                saveDate = null,
            ),
            AstronomyPicture(
                id = Date(Date().time),
                title = "Star Clusters Young and Old",
                explanation = null,
                copyright = "copyright",
                type = SourceType.Image,
                src = "https://apod.nasa.gov/apod/image/0703/saturnOccultation_Lawrence720.jpg",
                hsrc = 	"https://apod.nasa.gov/apod/image/0703/saturnOccultation_Lawrence720.jpg",
                saveDate = null,
            ),
            AstronomyPicture(
                id = Date(Date().time),
                title = "Star Clusters Young and Old",
                explanation = null,
                copyright = "copyright",
                type = SourceType.Image,
                src = "https://apod.nasa.gov/apod/image/1002/NGC1333ruiz900.jpg",
                hsrc = 	"ttps://apod.nasa.gov/apod/image/0609/m46m47_hetlage_big.jpg",
                saveDate = null,
            ),
            AstronomyPicture(
                id = Date(Date().time),
                title = "Star Clusters Young and Old",
                explanation = null,
                copyright = "copyright",
                type = SourceType.Image,
                src = "https://apod.nasa.gov/apod/image/gamma_3c279_egret.gif",
                hsrc = 	"https://apod.nasa.gov/apod/image/gamma_3c279_egret.gif",
                saveDate = null,
            ),
        ),
        onClick = {},
        onLike = {}
    )
}