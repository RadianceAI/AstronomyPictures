package by.radiance.space.picrures.ui.list.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.presenter.TodayPictureViewModel
import by.radiance.space.pictures.domain.usecase.today.TodayAstronomyPictureUseCase
import kotlinx.coroutines.launch

class TodayAstronomyPictureViewModel(
        private val todayAstronomyPictureUseCase: TodayAstronomyPictureUseCase,
) : ViewModel(), TodayPictureViewModel {
    private val _todayPicture = MutableLiveData<Picture>()
    override val todayPicture: LiveData<Picture> = _todayPicture

    override fun init() {
        viewModelScope.launch {
            _todayPicture.postValue(todayAstronomyPictureUseCase.get())
        }
    }
}