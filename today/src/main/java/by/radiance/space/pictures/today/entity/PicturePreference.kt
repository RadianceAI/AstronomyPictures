package by.radiance.space.pictures.today.entity

import com.google.gson.annotations.SerializedName

data class PicturePreference(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String?,
    @SerializedName("explanation")
    val explanation: String?,
    @SerializedName("copyright")
    val copyright: String?,
    @SerializedName("type")
    val type: String,
    @SerializedName("src")
    val src: String?,
    @SerializedName("hsrc")
    val hsrc: String?,
    @SerializedName("is_saved")
    val isSaved: Boolean,
    @SerializedName("save_date")
    val date: String?,
)