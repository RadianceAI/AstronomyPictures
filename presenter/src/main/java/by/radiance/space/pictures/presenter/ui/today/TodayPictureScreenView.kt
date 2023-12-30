package by.radiance.space.pictures.presenter.ui.today

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import by.radiance.space.pictures.presenter.ui.picture.PictureCard
import androidx.compose.ui.tooling.preview.Preview
import by.radiance.space.pictures.presenter.ui.theme.AstronomyPicturesTheme
import by.radiance.space.pictures.presenter.ui.utils.ErrorCard
import by.radiance.space.pictures.presenter.ui.utils.LoadingCard
import by.radiance.space.pictures.domain.entity.Id
import by.radiance.space.pictures.domain.entity.Image
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.presenter.state.PictureUiState
import java.util.*

@Composable
fun TodayPictureScreenView(
    modifier: Modifier = Modifier,
    picture: PictureUiState,
    onClick: (Picture) -> Unit,
    onLike: (Picture) -> Unit,
) {
    Scaffold(
        modifier = modifier
    ) { contentPadding ->
        when (picture) {
            is PictureUiState.Success -> {
                PictureCard(
                    modifier = Modifier.padding(contentPadding),
                    picture = picture.picture,
                    onClick = onClick
                )
            }

            is PictureUiState.Loading -> {
                LoadingCard()
            }

            is PictureUiState.Error -> {
                ErrorCard(error = "Picture is not ready yet.")
            }
        }
    }
}

@Preview
@Composable
fun TodayPictureScreenPreview() {
    AstronomyPicturesTheme {
        TodayPictureScreenView(
            picture = PictureUiState.Success(
                Picture(
                    id = Id("2024-12-24"),
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