package by.radiance.space.pictures.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(val huge: String, val light: String): Parcelable
