package by.radiance.space.pictures.domain.entity

import android.os.Parcelable
import android.text.format.DateUtils
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Id(
    val date: Date,
    val isRandom: Boolean = false,
): Parcelable {
    val isToday: Boolean
        get() = DateUtils.isToday(date.time)

    companion object {
        val today: Id
            get() = Id(Date())
    }
}
