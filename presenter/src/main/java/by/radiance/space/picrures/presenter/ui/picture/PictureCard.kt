package by.radiance.space.picrures.presenter.ui.picture

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
import by.radiance.space.picrures.presenter.R
import by.radiance.space.picrures.presenter.ui.theme.AstronomyPicturesTheme
import by.radiance.space.picrures.presenter.ui.theme.CardGray
import by.radiance.space.picrures.presenter.ui.utils.LoadingCard
import by.radiance.space.pictures.domain.utils.DateHelper

@Composable
fun PictureCard(
    modifier: Modifier = Modifier,
    picture: Picture,
    onClick: (Picture) -> Unit,
    onLike: (Picture) -> Unit
) {
    Card(
        modifier = modifier
            .wrapContentWidth()
            .clip(MaterialTheme.shapes.medium.copy(all = CornerSize(16.dp)))
            .clickable { onClick(picture) }
    ) {
        Box() {
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
                    .padding(15.dp)
                    .clip(RoundedCornerShape(5.dp, 5.dp, 16.dp, 16.dp))
                    .background(CardGray.copy(alpha = 0.2f))
            ) {
                Row(
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(end = 20.dp)
                            .weight(1f)
                    ) {
                        Text(
                            text = picture.title ?:"",
                            style = MaterialTheme.typography.h6.copy(fontSize = 15.sp),
                            color = Color.White,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        Text(
                            text =  DateHelper.getDate(picture.id.date),
                            style = MaterialTheme.typography.subtitle2.copy(fontSize = 12.sp, fontWeight = FontWeight.Light),
                            color = Color.White.copy(alpha = .8f),
                            maxLines = 1,
                        )
                    }
                    IconButton(
                        modifier = Modifier,
                        onClick = { onLike(picture) }
                    ) {
                        Icon(
                            painter = painterResource(
                                if (picture.isSaved) R.drawable.ic_dark_pink_active
                                else R.drawable.ic_dark_pink_defolt
                            ),
                            contentDescription = null,
                            modifier = modifier
                                .padding(8.dp),
                            tint = Color.Unspecified,
                        )
                    }
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
                id = Id(Date()),
                title = "Title",
                explanation = "Explanation",
                copyright = "cop",
                source = Image(light = "", huge = ""),
                isSaved = true,
                saveDate = Date()
            ),
            onClick = {},
            onLike = {},
        )
    }
}