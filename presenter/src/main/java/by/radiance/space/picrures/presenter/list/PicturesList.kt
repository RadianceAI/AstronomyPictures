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
import androidx.compose.ui.Alignment
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.presenter.state.PictureUiState
import by.radiance.space.pictures.domain.presenter.state.PicturesListUiState
import java.util.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PictureList(
    modifier: Modifier = Modifier,
    todayPicture: PictureUiState?,
    randomPicture: PictureUiState?,
    savedList: PicturesListUiState,
    onClick: (Picture) -> Unit,
    onLike: (Picture) -> Unit,
) {
    Scaffold(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(it)
        ) {
            TodayPictures(
                todayPicture = (todayPicture as PictureUiState.Success).picture,
                randomPicture = (randomPicture as PictureUiState.Success).picture,
                onClick = onClick,
                onLike = onLike,
            )

            LazyVerticalGrid(
                cells = GridCells.Fixed(3),
                content = {
                    items((savedList as PicturesListUiState.Success).pictures) {
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
    todayPicture: Picture?,
    randomPicture: Picture?,
    onClick: (Picture) -> Unit,
    onLike: (Picture) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .height(250.dp)
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
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .height(250.dp)
                .padding(4.dp)
        ) {
            if (randomPicture != null) {
                Picture(
                    modifier = Modifier.height(250.dp),
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