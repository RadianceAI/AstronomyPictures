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
import by.radiance.space.pictures.domain.utils.DateUtil
import by.radiance.space.pictures.domain.utils.minusDays
import by.radiance.space.pictures.presenter.BuildConfig
import by.radiance.space.pictures.presenter.ui.gallery.paging.PictureSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.util.Date

class GalleryViewModel(
    private val astronomyPictureRepository: RemoteAstronomyPictureRepository,
) : ViewModel() {

    val startDate = DateUtil.APODStartDate()
    val endDate = Date().minusDays(1)

    private val pagingConfig = PagingConfig(
        pageSize = pageSize,
        initialLoadSize = pageSize,
        enablePlaceholders = true,
        jumpThreshold = 3 * pageSize,
    )

    private val pager = Pager(config = pagingConfig) {
        PictureSource(
            startDate = startDate,
            endDate = endDate,
            astronomyPictureRepository = astronomyPictureRepository,
        )
    }

    private val scrollToChannel = MutableSharedFlow<Int>()

    val pictures: Flow<PagingData<Picture>> by lazy { pager.flow.cachedIn(viewModelScope) }
    val scrollTo: Flow<Int> = scrollToChannel

    fun onDateSelected(date: Date) {
        viewModelScope.launch {
            scrollToChannel.emit(DateUtil.daysBetweenDates(endDate, date))
        }
    }

    companion object {
        private val pageSize = 30
    }
}