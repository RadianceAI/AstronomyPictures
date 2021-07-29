package by.radiance.space.pictures.domain.presenter

import by.radiance.space.pictures.domain.entity.Picture
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import java.util.*

@ExperimentalCoroutinesApi
interface PictureListViewModel {
    val today: StateFlow<Picture>
    val random: StateFlow<Picture>
    val list: StateFlow<List<Picture>>

    fun filter(stateDate: Date, endDate: Date)
}