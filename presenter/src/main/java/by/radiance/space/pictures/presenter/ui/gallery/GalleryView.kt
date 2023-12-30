package by.radiance.space.pictures.presenter.ui.gallery

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.presenter.ui.picture.PictureCard
import kotlinx.coroutines.flow.Flow

@Composable
fun GalleryView(
    modifier: Modifier = Modifier,
    cellCount: Int,
    pictures: Flow<PagingData<Picture>>,
    onClick: (Picture) -> Unit,
    staggered: Boolean = false,
) {
    val lazyItems = pictures.collectAsLazyPagingItems()

    if (staggered) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(cellCount),
            modifier = modifier,
        ) {
            pageItems(lazyItems) {item ->
                if (item == null) return@pageItems

                PictureCard(
                    modifier = Modifier
                        .defaultMinSize(minHeight = 150.dp),
                    picture = item,
                    onClick = onClick,
                )
            }
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(cellCount),
            modifier = modifier,
        ) {
            pageItems(lazyItems) { item ->
                if (item == null) return@pageItems

                PictureCard(
                    modifier = Modifier
                        .height(150.dp),
                    picture = item,
                    onClick = onClick,
                )
            }
        }
    }
}

fun <T : Any> LazyStaggeredGridScope.pageItems(
    lazyPagingItems: LazyPagingItems<T>,
    content: @Composable LazyStaggeredGridScope.(value: T?) -> Unit,
) {
    items(lazyPagingItems.itemCount) { index ->
        this@pageItems.content(lazyPagingItems.getAsState(index).value)
    }
}

fun <T : Any> LazyGridScope.pageItems(
    lazyPagingItems: LazyPagingItems<T>,
    content: @Composable LazyGridScope.(value: T?) -> Unit,
) {
    items(lazyPagingItems.itemCount) { index ->
        this@pageItems.content(lazyPagingItems.getAsState(index).value)
    }
}
