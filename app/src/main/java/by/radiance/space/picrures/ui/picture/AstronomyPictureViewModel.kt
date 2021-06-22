package by.radiance.space.picrures.ui.picture

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.radiance.space.pictures.domain.entity.AstronomyPicture
import by.radiance.space.pictures.domain.entity.PictureId
import by.radiance.space.pictures.domain.usecase.saved.SavedAstronomyPicturesUseCase
import by.radiance.space.pictures.domain.usecase.today.TodayAstronomyPictureUseCase
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class AstronomyPictureViewModel(
    private val savedAstronomyPicturesUseCase: SavedAstronomyPicturesUseCase,
    private val todayAstronomyPictureUseCase: TodayAstronomyPictureUseCase,
): PictureViewModel, ViewModel() {
    private val _picture = MutableLiveData<AstronomyPicture>()
    override val picture: LiveData<AstronomyPicture> = _picture

    override fun setPicture(id: PictureId) {
        viewModelScope.launch {
            if (id.isToday) {
                _picture.postValue(todayAstronomyPictureUseCase.get())
            } else {
                _picture.postValue(savedAstronomyPicturesUseCase.get(id))
            }
        }
    }
}