package by.radiance.space.picrures.presenter.picture

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import by.radiance.space.pictures.domain.entity.Image
import by.radiance.space.pictures.domain.entity.Picture
import coil.compose.SubcomposeAsyncImage

@Composable
fun SmallPictureCard(
    modifier: Modifier = Modifier,
    picture: Picture,
    onClick: (Picture) -> Unit,
) {

    SubcomposeAsyncImage(
        modifier = modifier
            .clickable { onClick(picture) }
            .fillMaxWidth()
            .fillMaxHeight(),
        model = (picture.source as Image).light,
        contentDescription = picture.explanation,
        loading = { CircularProgressIndicator() },
        contentScale = ContentScale.Crop
    )
}