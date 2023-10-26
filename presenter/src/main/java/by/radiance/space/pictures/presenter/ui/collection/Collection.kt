package by.radiance.space.pictures.presenter.ui.collection

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import by.radiance.space.pictures.presenter.ui.utils.WindowSize
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.presenter.state.PicturesListUiState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Collection(
    windowHeight: WindowSize,
    modifier: Modifier = Modifier,
    list: PicturesListUiState?,
    onClick: (Picture) -> Unit,
) {
//    Scaffold(
//        modifier = modifier
//    ) {
//        val gridCount = when (windowHeight) {
//            WindowSize.Compact -> 5
//            else -> 3
//        }

//        LazyVerticalGrid(
//            modifier = Modifier
//                .fillMaxSize(),
//                cells = GridCells.Fixed(gridCount),
//            content = {
//                (list as PicturesListUiState.Success).pictures.forEach { group ->
//                    item(
//                        span = {
//                            GridItemSpan(gridCount)
//                        }
//                    ) {
//                        Text(
//                            modifier = Modifier.padding(8.dp),
//                            text = group.key.name
//                        )
//                    }
//
//                    val fillItemsCount = gridCount - group.value.count() % gridCount
//
//                    items(group.value, spans = {
//                        GridItemSpan(1)
//                    }) {
//                        SmallPictureCard(
//                            modifier = Modifier
//                                .height(130.dp),
//                            picture = it,
//                            onClick = onClick,
//                        )
//                    }
//
//                    repeat((0 until fillItemsCount).count()) {
//                        item {
//                            Box(modifier = Modifier.fillMaxSize()) {}
//                        }
//                    }
//                }
//            })

//    }
}
