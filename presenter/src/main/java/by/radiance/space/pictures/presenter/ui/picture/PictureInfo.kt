package by.radiance.space.pictures.presenter.ui.picture

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.radiance.space.pictures.domain.utils.DateUtil
import by.radiance.space.pictures.presenter.ui.theme.AstronomyPicturesTheme
import java.util.Date

@Composable
fun PictureInfo(
    modifier: Modifier = Modifier,
    date: String,
    title: String
) {
    Row(
        modifier = modifier.padding(4.dp)
    ) {
        Text(
            modifier = Modifier
                .weight(1f),
            text = title ?: "",
            style = MaterialTheme.typography.h6,
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 8.dp),
            text = date,
            style = MaterialTheme.typography.subtitle2.copy(
                fontSize = 12.sp,
                fontWeight = FontWeight.Light
            ),
            color = Color.White.copy(alpha = .5f),
            maxLines = 1,
        )
    }
}

@Preview
@Composable
fun PicrureInfoPreview() {
    AstronomyPicturesTheme {
        PictureInfo(
            modifier = Modifier.width(400.dp).wrapContentHeight().background(Color.Black),
            date = DateUtil.getDate(Date()),
            title = "Today picture",
        )
    }
}