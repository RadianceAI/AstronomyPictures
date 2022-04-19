package by.radiance.space.picrures.presenter.ui.new

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import by.radiance.space.picrures.presenter.ui.picture.PictureCard
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import by.radiance.space.picrures.presenter.ui.theme.AstronomyPicturesTheme
import by.radiance.space.pictures.domain.entity.Id
import by.radiance.space.pictures.domain.entity.Image
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.presenter.state.PictureUiState
import java.util.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewPictures(
    modifier: Modifier = Modifier,
    todayPicture: PictureUiState?,
    randomPicture: PictureUiState?,
    onClick: (Picture) -> Unit,
    onLike: (Picture) -> Unit,
) {
    Scaffold(
        modifier = modifier
    ) {
        TodayPictures(
            modifier = Modifier
                .fillMaxSize(),
            todayPicture = (todayPicture as PictureUiState.Success).picture,
            randomPicture = (randomPicture as PictureUiState.Success).picture,
            onClick = onClick,
            onLike = onLike,
        )
    }
}

@Composable
fun TodayPictures(
    modifier: Modifier = Modifier,
    todayPicture: Picture?,
    randomPicture: Picture?,
    onClick: (Picture) -> Unit,
    onLike: (Picture) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp),
                text = "Today"
            )
            Box(
                modifier = Modifier
                    .padding(4.dp)
            ) {
                if (todayPicture != null) {
                    PictureCard(
                        picture = todayPicture,
                        onClick = onClick,
                        onLike = onLike
                    )
                } else {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
            }
        }
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp),
                text = "Random"
            )
            Box(
                modifier = Modifier
                    .padding(4.dp)
            ) {
                if (randomPicture != null) {
                    PictureCard(
                        picture = randomPicture,
                        onClick = onClick,
                        onLike = onLike
                    )
                } else {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ListPreview() {
    AstronomyPicturesTheme {
        NewPictures(
            todayPicture = PictureUiState.Success(
                Picture(
                    id = Id(Date()),
                    title = "Title",
                    explanation = "Explanation",
                    copyright = "cop",
                    source = Image(light = "", huge = ""),
                    isSaved = true,
                    saveDate = Date()
                )
            ),
            randomPicture = PictureUiState.Success(
                Picture(
                    id = Id(Date()),
                    title = "Title",
                    explanation = "Explanation",
                    copyright = "cop",
                    source = Image(light = "", huge = ""),
                    isSaved = true,
                    saveDate = Date()
                )
            ),
            onClick = {},
            onLike = {}
        )
    }
}