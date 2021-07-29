package by.radiance.space.picrures.ui.picture.viewModel

import androidx.lifecycle.*
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.entity.Id
import by.radiance.space.pictures.domain.entity.QrCode
import by.radiance.space.pictures.domain.presenter.PictureDetailsViewModel
import by.radiance.space.pictures.domain.usecase.save.SaveAstronomyPictureUseCase
import by.radiance.space.pictures.domain.usecase.saved.SavedAstronomyPicturesUseCase
import by.radiance.space.pictures.domain.usecase.today.TodayAstronomyPictureUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AstronomyPictureViewModel(
    private val savedAstronomyPicturesUseCase: SavedAstronomyPicturesUseCase,
    private val saveAstronomyPictureUseCase: SaveAstronomyPictureUseCase,
    private val todayAstronomyPictureUseCase: TodayAstronomyPictureUseCase,
): PictureDetailsViewModel, ViewModel() {
    private val _astronomyPicture = MutableLiveData<Picture>()
    override val picture: LiveData<Picture> = _astronomyPicture

    private val _qrCode = MutableLiveData<QrCode>()
    override val qrCode: LiveData<QrCode> = _qrCode

    override fun init(id: Id) {
        viewModelScope.launch {
            val todayPicture = if (id.isToday) {
                todayAstronomyPictureUseCase.get()
            } else {
                savedAstronomyPicturesUseCase.get(id)
            }

            todayPicture?.let {
                launch(Dispatchers.Main) {
                    _astronomyPicture.postValue(it)
                }
            }
        }
    }

    override fun save() {
        _astronomyPicture.value?.let { currentPicture ->
            viewModelScope.launch {
                if (currentPicture.isSaved) {
                    saveAstronomyPictureUseCase.delete(currentPicture)
                    launch(Dispatchers.Main) {
                        _astronomyPicture.postValue(currentPicture.copy(isSaved = false))
                    }
                } else {
                    saveAstronomyPictureUseCase.save(currentPicture)
                    launch(Dispatchers.Main) {
                        _astronomyPicture.postValue(currentPicture.copy(isSaved = true))
                    }
                }
            }
        }
    }

    override fun setToBackground() {

    }

    override fun setToLockScreen() {

    }
}