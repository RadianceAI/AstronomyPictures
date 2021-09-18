package by.radiance.space.pictures.today.mapper

import by.radiance.space.pictures.domain.entity.Id
import by.radiance.space.pictures.domain.entity.Image
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.entity.Video
import by.radiance.space.pictures.today.entity.PicturePreference
import by.radiance.space.pictures.today.utils.DateUtils
import by.radiance.space.pictures.today.utils.MediaType

class PictureMapper {
    fun map(picture: PicturePreference): Picture {
        return Picture(
            id = Id(DateUtils.getDate(picture.id)),
            title = picture.title,
            explanation = picture.explanation,
            copyright = picture.copyright,
            source = when (picture.type) {
                MediaType.Image -> Image(huge = picture.hsrc ?: "", light = picture.src ?: "")
                MediaType.Video -> Video(thumbnail = picture.src ?: "", src = picture.hsrc ?: "")
                else -> Image("", "")
            },
            isSaved = picture.isSaved,
            saveDate = null,
        )
    }

    fun map(picture: Picture): PicturePreference {
        return PicturePreference(
            id = DateUtils.getDate(picture.id.date),
            title = picture.title,
            explanation = picture.explanation,
            copyright = picture.copyright,
            type = when (picture.source) {
                is Video -> MediaType.Video
                is Image -> MediaType.Image
                else -> MediaType.Other
            },
            src = when (picture.source) {
                is Video -> (picture.source as Video).thumbnail
                is Image -> (picture.source as Image).light
                else -> ""
            },
            hsrc = when (picture.source) {
                is Video -> (picture.source as Video).src
                is Image -> (picture.source as Image).huge
                else -> ""
            },
            isSaved = picture.isSaved,
            date = null,
        )
    }
}