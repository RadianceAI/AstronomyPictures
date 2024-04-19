package by.radiance.space.pictures.presenter.ui.details

import android.graphics.drawable.Drawable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import by.radiance.space.pictures.domain.entity.Id
import by.radiance.space.pictures.domain.presenter.state.PictureUiState
import by.radiance.space.pictures.presenter.ui.error.ErrorCard
import by.radiance.space.pictures.presenter.ui.progress.Progress
import by.radiance.space.pictures.presenter.ui.theme.AstronomyPicturesTheme
import by.radiance.space.pictures.presenter.ui.utils.WindowSize
import coil.compose.SubcomposeAsyncImage

@Composable
fun Image(
    heightWindowSize: WindowSize,
    pictureUiState: PictureUiState,
    onDrawableReceived: (Drawable) -> Unit
) {
    when (pictureUiState) {
        is PictureUiState.Success -> {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .fillMaxSize(),
                model = pictureUiState.picture.source.huge,
                contentDescription = pictureUiState.picture.explanation,
                loading = {
                    Progress()
                },
                contentScale =  ContentScale.Fit ,
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

@Preview(
    widthDp = 400,
    heightDp = 400,
)
@Composable
fun ImageLoadingPreview() {
    AstronomyPicturesTheme {
        Image(
            heightWindowSize = WindowSize.Compact,
            pictureUiState = PictureUiState.Error(Throwable()),
            onDrawableReceived = {

            },
        )
    }
}

@Preview(
    widthDp = 400,
    heightDp = 400,
)
@Composable
fun ImageErrorPreview() {
    AstronomyPicturesTheme {
        Image(
            heightWindowSize = WindowSize.Compact,
            pictureUiState = PictureUiState.Loading,
            onDrawableReceived = {

            },
        )
    }
}

@Preview(
    widthDp = 400,
    heightDp = 400,
)
@Composable
fun ImagePreview() {
    AstronomyPicturesTheme {
        Image(
            heightWindowSize = WindowSize.Compact,
            pictureUiState = PictureUiState.Success(
                picture = by.radiance.space.pictures.domain.entity.Picture(
                    id = Id(
                        date = ""
                    ),
                    title = null,
                    explanation = null,
                    copyright = null,
                    source = by.radiance.space.pictures.domain.entity.Image(huge = "", light = ""),
                    isSaved = false
                )
            ),
            onDrawableReceived = {

            },
        )
    }
}
