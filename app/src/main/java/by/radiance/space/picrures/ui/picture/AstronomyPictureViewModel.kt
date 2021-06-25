package by.radiance.space.picrures.ui.picture

import androidx.lifecycle.*
import by.radiance.space.pictures.domain.entity.AstronomyPicture
import by.radiance.space.pictures.domain.entity.PictureId
import by.radiance.space.pictures.domain.usecase.save.SaveAstronomyPictureUseCase
import by.radiance.space.pictures.domain.usecase.saved.SavedAstronomyPicturesUseCase
import by.radiance.space.pictures.domain.usecase.today.TodayAstronomyPictureUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class AstronomyPictureViewModel(
    private val savedAstronomyPicturesUseCase: SavedAstronomyPicturesUseCase,
    private val saveAstronomyPictureUseCase: SaveAstronomyPictureUseCase,
    private val todayAstronomyPictureUseCase: TodayAstronomyPictureUseCase,
): PictureViewModel, ViewModel() {
    private val _picture = MutableLiveData<AstronomyPicture>()
    override val picture: LiveData<AstronomyPicture> = _picture

    override fun setPicture(id: PictureId) {
        viewModelScope.launch {
            val todayPicture = if (id.isToday) {
                todayAstronomyPictureUseCase.get()
            } else {
                savedAstronomyPicturesUseCase.get(id)
            }

            todayPicture?.let {
                _picture.postValue(it)
            }
        }
    }

    override fun onSaveClicked() {
        _picture.value?.let { currentPicture ->
            viewModelScope.launch {
                if (currentPicture.isSaved) {
                    saveAstronomyPictureUseCase.delete(currentPicture)
                    launch(Dispatchers.Main) {
                        _picture.postValue(currentPicture.copy(isSaved = false))
                    }
                } else {
                    saveAstronomyPictureUseCase.save(currentPicture)
                    launch(Dispatchers.Main) {
                        _picture.postValue(currentPicture.copy(isSaved = true))
                    }
                }
            }
        }
    }
}