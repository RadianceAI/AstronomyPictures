package by.radiance.space.pictures.presenter.ui.gallery

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.presenter.ui.picture.PictureCard
import by.radiance.space.pictures.presenter.ui.utils.WindowSize
import kotlinx.coroutines.flow.Flow

@Composable
fun GalleryView(
    modifier: Modifier = Modifier,
    pictures: Flow<PagingData<Picture>>,
) {
    val lazyItems = pictures.collectAsLazyPagingItems()

    LazyColumn(modifier = modifier) {
        items(lazyItems) { item ->
            if (item == null) return@items

            PictureCard(picture = item, onClick = {}, onLike = {})
        }
    }
}