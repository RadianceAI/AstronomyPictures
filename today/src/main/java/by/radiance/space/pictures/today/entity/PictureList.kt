package by.radiance.space.pictures.today.entity

import com.google.gson.annotations.SerializedName

data class PictureList(
    @SerializedName("list")
    val list: MutableList<PicturePreference>,
)