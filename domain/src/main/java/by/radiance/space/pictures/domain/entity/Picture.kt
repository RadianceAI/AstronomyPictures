package by.radiance.space.pictures.domain.entity

import java.util.*

data class Picture(
        val id: Id,
        val title: String?,
        val explanation: String?,
        val copyright: String?,
        val source: Source,
        val isSaved: Boolean,
        val saveDate: Date?,
)