package by.radiance.space.picrures.ui.picture.viewModel

import androidx.lifecycle.*

class AstronomyPictureViewModel(
//    private val savedAstronomyPicturesUseCase: SavedAstronomyPicturesUseCase,
//    private val saveAstronomyPictureUseCase: SaveAstronomyPictureUseCase,
//    private val todayAstronomyPictureUseCase: TodayAstronomyPictureUseCase,
): ViewModel() {
//    private val _astronomyPicture = MutableLiveData<Picture>()
//    override val picture: LiveData<Picture> = _astronomyPicture
//
//    private val _qrCode = MutableLiveData<QrCode>()
//    override val qrCode: LiveData<QrCode> = _qrCode
//
//    override fun init(id: Id) {
//        viewModelScope.launch {
//            val todayPicture = if (id.isToday) {
//                todayAstronomyPictureUseCase.get()
//            } else {
//                savedAstronomyPicturesUseCase.get(id)
//            }
//
//            todayPicture?.let {
//                launch(Dispatchers.Main) {
//                    _astronomyPicture.postValue(it)
//                }
//            }
//        }
//    }
//
//    override fun save() {
//        _astronomyPicture.value?.let { currentPicture ->
//            viewModelScope.launch {
//                if (currentPicture.isSaved) {
//                    saveAstronomyPictureUseCase.delete(currentPicture)
//                    launch(Dispatchers.Main) {
//                        _astronomyPicture.postValue(currentPicture.copy(isSaved = false))
//                    }
//                } else {
//                    saveAstronomyPictureUseCase.save(currentPicture)
//                    launch(Dispatchers.Main) {
//                        _astronomyPicture.postValue(currentPicture.copy(isSaved = true))
//                    }
//                }
//            }
//        }
//    }
//
//    override fun setToBackground() {
//
//    }
//
//    override fun setToLockScreen() {
//
//    }
}