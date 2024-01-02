package by.radiance.space.pictures.domain.entity

import android.os.Parcelable
import android.text.format.DateUtils
import by.radiance.space.pictures.domain.utils.DateUtil
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Id(
    val date: String,
): Parcelable

fun Date.toId(): Id {
    return Id(DateUtil.formatId(this))
}