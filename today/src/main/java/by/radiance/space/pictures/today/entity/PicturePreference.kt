package by.radiance.space.pictures.today.entity

data class PicturePreference(
    val id: String,
    val title: String?,
    val explanation: String?,
    val copyright: String?,
    val src: String?,
    val hsrc: String?,
    val isSaved: Boolean,
)