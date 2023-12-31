package by.radiance.space.pictures.presenter.ui.gallery

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.utils.DateUtil
import by.radiance.space.pictures.presenter.ui.picture.PictureCard
import by.radiance.space.pictures.presenter.utils.showDatePicker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.Date

@Composable
fun GalleryView(
    modifier: Modifier = Modifier,
    cellCount: Int,
    pictures: Flow<PagingData<Picture>>,
    scrollTo: Flow<Int>,
    onClick: (Picture) -> Unit,
    onDateSelected: (Date) -> Unit,
    staggered: Boolean = false,
) {
    val lazyItems = pictures.collectAsLazyPagingItems()
    val context = LocalContext.current

    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            CalendarFAB {
                showDatePicker(
                    context = context,
                    onDateSelected = onDateSelected,
                    maxDate = Date().time,
                    minDate = DateUtil.APODStartDate().time,
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { innerPadding ->
       LazyPicturesGrid(
           staggered = staggered,
           cellCount = cellCount,
           innerPadding = innerPadding,
           lazyItems = lazyItems,
           scrollTo = scrollTo,
           onClick = onClick,
       )
    }
}

@Composable
fun LazyPicturesGrid(
    staggered: Boolean,
    cellCount: Int,
    innerPadding: PaddingValues,
    lazyItems: LazyPagingItems<Picture>,
    scrollTo: Flow<Int>,
    onClick: (Picture) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    val scrollGridToItem = if (staggered) {
        val state = rememberLazyStaggeredGridState()

        LazyVerticalStaggeredGrid(
            state = state,
            columns = StaggeredGridCells.Fixed(cellCount),
            modifier = Modifier.padding(innerPadding),
        ) {
            pageItems(lazyItems) { item ->
                if (item == null) return@pageItems

                PictureCard(
                    modifier = Modifier
                        .defaultMinSize(minHeight = 150.dp),
                    picture = item,
                    onClick = onClick,
                )
            }
        }

        state::scrollToItem
    } else {
        val state = rememberLazyGridState()

        LazyVerticalGrid(
            state = state,
            columns = GridCells.Fixed(cellCount),
            modifier = Modifier.padding(innerPadding),
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

        state::scrollToItem
    }

    LaunchedEffect(scrollTo) {
        coroutineScope.launch {
            scrollTo.collect { index ->
                scrollGridToItem(index, 0)
            }
        }
    }
}

@Composable
fun CalendarFAB(onCalendarClick: () -> Unit) {
    FloatingActionButton(
        modifier = Modifier,
        onClick = onCalendarClick,
        contentColor = Color.White,
        backgroundColor = Color.DarkGray,
    ) {
        Icon(
            imageVector = Icons.Filled.CalendarMonth,
            contentDescription = "pick date",
        )
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
