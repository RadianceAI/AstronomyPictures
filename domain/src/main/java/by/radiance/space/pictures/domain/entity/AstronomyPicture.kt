package by.radiance.space.pictures.domain.entity

import java.util.*

data class AstronomyPicture(
    val id: PictureId,
    val title: String?,
    val explanation: String?,
    val copyright: String?,
    val source: PictureSource,
    val isSaved: Boolean
)