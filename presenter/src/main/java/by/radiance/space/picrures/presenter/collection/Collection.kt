package by.radiance.space.picrures.presenter.collection

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import by.radiance.space.picrures.presenter.picture.PictureCard
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.presenter.state.PicturesListUiState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Collection(
    modifier: Modifier = Modifier,
    list: PicturesListUiState?,
    onClick: (Picture) -> Unit,
    onLike: (Picture) -> Unit,
) {
    Scaffold(
        modifier = modifier
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize(),
                cells = GridCells.Fixed(3),
            content = {
                items((list as PicturesListUiState.Success).pictures) {
                    PictureCard(
                        modifier = Modifier
                            .height(150.dp)
                            .padding(4.dp)
                        ,
                        picture = it,
                        onClick = onClick,
                        onLike = onLike
                    )
                }
            })

    }
}
