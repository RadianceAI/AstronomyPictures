package by.radiance.space.pictures.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*


@Parcelize
data class Picture(
        val id: Id,
        val title: String?,
        val explanation: String?,
        val copyright: String?,
        val source: Image,
        val isSaved: Boolean,
        val saveDate: Date?,
): Parcelable