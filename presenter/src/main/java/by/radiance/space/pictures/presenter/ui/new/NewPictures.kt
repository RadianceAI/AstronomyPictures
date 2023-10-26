package by.radiance.space.pictures.presenter.ui.new

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import by.radiance.space.pictures.presenter.ui.picture.PictureCard
import androidx.compose.material.Text
import androidx.compose.ui.tooling.preview.Preview
import by.radiance.space.pictures.presenter.ui.theme.AstronomyPicturesTheme
import by.radiance.space.pictures.presenter.ui.utils.ErrorCard
import by.radiance.space.pictures.presenter.ui.utils.LoadingCard
import by.radiance.space.pictures.presenter.ui.utils.WindowSize
import by.radiance.space.pictures.domain.entity.Id
import by.radiance.space.pictures.domain.entity.Image
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.presenter.state.PictureUiState
import java.util.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewPictures(
    windowHeight: WindowSize,
    modifier: Modifier = Modifier,
    todayPicture: PictureUiState?,
    randomPicture: PictureUiState?,
    onClick: (Picture) -> Unit,
    onLike: (Picture) -> Unit,
) {
    Scaffold(
        modifier = modifier
    ) {

        when (windowHeight) {
            WindowSize.Compact -> {
                TodayPicturesCompact(
                    modifier = Modifier
                        .fillMaxSize(),
                    todayPicture = todayPicture,
                    randomPicture = randomPicture,
                    onClick = onClick,
                    onLike = onLike,
                )
            }
            else -> {
                TodayPictures(
                    modifier = Modifier
                        .fillMaxSize(),
                    todayPicture = todayPicture,
                    randomPicture = randomPicture,
                    onClick = onClick,
                    onLike = onLike,
                )
            }
        }
    }
}

@Composable
fun TodayPicturesCompact(
    modifier: Modifier = Modifier,
    todayPicture: PictureUiState?,
    randomPicture: PictureUiState?,
    onClick: (Picture) -> Unit,
    onLike: (Picture) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        PictureContent(
            modifier = Modifier.weight(1f),
            title = "Today",
            pictureState = todayPicture,
            onClick = onClick,
            onLike = onLike
        )
        PictureContent(
            modifier = Modifier.weight(1f),
            title = "Random",
            pictureState = randomPicture,
            onClick = onClick,
            onLike = onLike
        )
    }
}

@Composable
fun TodayPictures(
    modifier: Modifier = Modifier,
    todayPicture: PictureUiState?,
    randomPicture: PictureUiState?,
    onClick: (Picture) -> Unit,
    onLike: (Picture) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        PictureContent(
            modifier = Modifier.weight(1f),
            title = "Today",
            pictureState = todayPicture,
            onClick = onClick,
            onLike = onLike
        )
        PictureContent(
            modifier = Modifier.weight(1f),
            title = "Random",
            pictureState = randomPicture,
            onClick = onClick,
            onLike = onLike
        )
    }
}

@Composable
private fun PictureContent(
    modifier: Modifier = Modifier,
    title: String,
    pictureState: PictureUiState?,
    onClick: (Picture) -> Unit,
    onLike: (Picture) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp),
            text = title
        )
        Box(
            modifier = Modifier
                .padding(4.dp)
        ) {
            when (pictureState) {
                is PictureUiState.Success -> {
                    PictureCard(
                        picture = pictureState.picture,
                        onClick = onClick,
                        onLike = onLike
                    )
                }
                is PictureUiState.Loading -> {
                    LoadingCard()
                }
                is PictureUiState.Error -> {
                    ErrorCard(error = "Picture is not ready yet.")
                }
                else -> Unit
            }
        }
    }
}

@Preview
@Composable
fun ListPreview() {
    AstronomyPicturesTheme {
        NewPictures(
            windowHeight = WindowSize.Compact,
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