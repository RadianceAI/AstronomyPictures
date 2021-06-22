package by.radiance.space.picrures.ui.list

import androidx.lifecycle.LiveData
import by.radiance.space.pictures.domain.entity.AstronomyPicture

interface ListPictureViewModel {
   val pictures: LiveData<List<AstronomyPicture>>

   fun init()
}