package by.radiance.space.pictures.domain.entity

import android.annotation.SuppressLint
import android.os.Parcelable
import android.text.format.DateUtils
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class PictureId(
    val date: Date
): Parcelable {
    val isToday: Boolean
        get() = DateUtils.isToday(date.time)

    companion object {
        val today: PictureId
            get() = PictureId(Date())
    }
}
