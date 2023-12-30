package by.radiance.space.pictures.presenter.ui.picture

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.radiance.space.pictures.domain.entity.Id
import by.radiance.space.pictures.domain.entity.Image
import by.radiance.space.pictures.domain.entity.Picture
import coil.compose.SubcomposeAsyncImage
import java.util.*
import by.radiance.space.pictures.presenter.ui.theme.AstronomyPicturesTheme
import by.radiance.space.pictures.presenter.ui.theme.CardGray
import by.radiance.space.pictures.presenter.ui.utils.LoadingCard
import by.radiance.space.pictures.domain.utils.DateUtil

@Composable
fun PictureCard(
    modifier: Modifier = Modifier,
    picture: Picture,
    onClick: (Picture) -> Unit,
) {
    Card(
        modifier = modifier
            .wrapContentWidth()
            .clickable { onClick(picture) }
    ) {
        Box {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                model = picture.source.light,
                contentDescription = picture.explanation,
                loading = { LoadingCard() },
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(CardGray.copy(alpha = 0.2f))
            ) {
                Row(
                    modifier = Modifier.padding(4.dp)
                ) {
                        Text(
                            modifier = Modifier
                                .weight(1f),
                            text = picture.title ?:"",
                            style = MaterialTheme.typography.h6,
                            color = Color.White,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        Text(
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = 8.dp),
                            text =  DateUtil.getDate(DateUtil.parseId(picture.id.date)!!),
                            style = MaterialTheme.typography.subtitle2.copy(fontSize = 12.sp, fontWeight = FontWeight.Light),
                            color = Color.White.copy(alpha = .5f),
                            maxLines = 1,
                        )
                }
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
                isSaved = true,
                saveDate = Date()
            ),
        ) {}
    }
}