package by.radiance.space.pictures.presenter.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.repository.RemoteAstronomyPictureRepository
import by.radiance.space.pictures.presenter.ui.gallery.paging.PictureSource
import kotlinx.coroutines.flow.Flow

class GalleryViewModel(
    private val astronomyPictureRepository: RemoteAstronomyPictureRepository,
) : ViewModel() {
    private val pager = Pager(PagingConfig(pageSize = 10)) {
        Log.d("TAG_TAG", "pager created")
        PictureSource(astronomyPictureRepository = astronomyPictureRepository)
    }

    val pictures: Flow<PagingData<Picture>> by lazy { pager.flow.cachedIn(viewModelScope) }
}