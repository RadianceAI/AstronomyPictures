package by.radiance.space.pictures.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Picture(
    val id: Id,
    val title: String?,
    val explanation: String?,
    val copyright: String?,
    val source: Image,
    val isSaved: Boolean,
): Parcelable