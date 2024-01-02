package by.radiance.space.pictures.presenter.ui.picture

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.radiance.space.pictures.domain.entity.Id
import by.radiance.space.pictures.domain.utils.DateUtil
import by.radiance.space.pictures.presenter.ui.theme.AstronomyPicturesTheme
import by.radiance.space.pictures.presenter.ui.theme.CardGray
import java.util.Date

@Composable
fun PicturePlaceHolder(
    modifier: Modifier = Modifier,
    id: Id,
    onClick: (Id) -> Unit
) {
    Card(
        modifier = modifier
            .wrapContentWidth()
            .clickable { onClick(id) }
    ) {
        Box(
            modifier = Modifier
                .background(Color.LightGray)
                .fillMaxWidth()
                .fillMaxHeight(),
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(CardGray.copy(alpha = 0.2f))
            ) {
                PictureInfo(
                    date = DateUtil.getDate(DateUtil.parseId(id.date)!!),
                    title = "..."
                )
            }
        }
    }
}

@Preview
@Composable
fun PicturePlaceHolderPreview() {
    AstronomyPicturesTheme {
        PicturePlaceHolder(
            modifier = Modifier
                .height(150.dp),
            id = Id(DateUtil.formatId(Date())),
            onClick = { },
        )
    }
}