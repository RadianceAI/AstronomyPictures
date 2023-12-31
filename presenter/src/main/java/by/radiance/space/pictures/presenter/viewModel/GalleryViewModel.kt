package by.radiance.space.pictures.presenter.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.repository.RemoteAstronomyPictureRepository
import by.radiance.space.pictures.presenter.ui.gallery.paging.PictureSource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import java.util.Date

class GalleryViewModel(
    private val astronomyPictureRepository: RemoteAstronomyPictureRepository,
) : ViewModel() {

    private val pager = Pager(config = PagingConfig(pageSize = 10, enablePlaceholders = true)) {
        PictureSource(astronomyPictureRepository = astronomyPictureRepository)
    }

    private val scrollToChannel = Channel<Int>()

    val pictures: Flow<PagingData<Picture>> by lazy { pager.flow.cachedIn(viewModelScope) }
    val scrollTo: Flow<Int> = scrollToChannel.consumeAsFlow()

    fun selectDate(date: Date) {
        viewModelScope.launch {
            scrollToChannel.send(0)
        }
    }
}