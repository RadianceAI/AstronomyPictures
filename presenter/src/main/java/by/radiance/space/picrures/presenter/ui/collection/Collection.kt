package by.radiance.space.picrures.presenter.ui.collection

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import by.radiance.space.picrures.presenter.ui.picture.SmallPictureCard
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.presenter.state.PicturesListUiState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Collection(
    modifier: Modifier = Modifier,
    list: PicturesListUiState?,
    onClick: (Picture) -> Unit,
) {
    Scaffold(
        modifier = modifier
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize(),
                cells = GridCells.Fixed(3),
            content = {
                (list as PicturesListUiState.Success).pictures.forEach { group ->
                    item(
                        span = {
                            GridItemSpan(3)
                        }
                    ) {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = group.key.name
                        )
                    }

                    val fillItemsCount = 3 - group.value.count() % 3

                    items(group.value, spans = {
                        GridItemSpan(1)
                    }) {
                        SmallPictureCard(
                            modifier = Modifier
                                .height(130.dp),
                            picture = it,
                            onClick = onClick,
                        )
                    }

                    repeat((0 until fillItemsCount).count()) {
                        item {
                            Box(modifier = Modifier.fillMaxSize()) {}
                        }
                    }
                }
            })

    }
}
