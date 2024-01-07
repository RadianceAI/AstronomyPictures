package by.radiance.space.pictures.presenter.ui.picture

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import by.radiance.space.pictures.domain.entity.Id
import by.radiance.space.pictures.domain.entity.Image
import by.radiance.space.pictures.domain.entity.Picture
import coil.compose.SubcomposeAsyncImage
import by.radiance.space.pictures.presenter.ui.theme.AstronomyPicturesTheme
import by.radiance.space.pictures.presenter.ui.theme.CardGray
import by.radiance.space.pictures.domain.utils.DateUtil

@Composable
fun PictureCard(
    modifier: Modifier = Modifier,
    picture: Picture,
    onClick: (Id) -> Unit,
) {
    Card(
        modifier = modifier
            .wrapContentWidth()
            .clickable { onClick(picture.id) },
    ) {
        Box {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                model = picture.source.light,
                contentDescription = picture.explanation,
                loading = {
                    PicturePlaceHolder(
                        id = picture.id,
                        onClick = onClick,
                    )
                },
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(CardGray.copy(alpha = 0.2f))
            ) {
                PictureInfo(
                    date = DateUtil.getDate(DateUtil.parseId(picture.id.date)),
                    title = picture.title ?: ""
                )
            }
        }
    }
}

@Preview(
    widthDp = 400,
    heightDp = 400,
)
@Composable
fun PicturePreview() {
    AstronomyPicturesTheme {
        PictureCard(
            picture = Picture(
                id = Id("0001-01-01"),
                title = "Title",
                explanation = "Explanation",
                copyright = "cop",
                source = Image(light = "", huge = ""),
                isSaved = true
            ),
        ) {}
    }
}