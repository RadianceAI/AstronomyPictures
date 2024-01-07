package by.radiance.space.pictures.today.mapper

import by.radiance.space.pictures.domain.entity.Id
import by.radiance.space.pictures.domain.entity.Image
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.today.entity.PicturePreference
import by.radiance.space.pictures.today.utils.MediaType

class PictureMapper {
    fun map(picture: PicturePreference): Picture {
        return Picture(
            id = Id(picture.id),
            title = picture.title,
            explanation = picture.explanation,
            copyright = picture.copyright,
            source =Image(huge = picture.hsrc ?: "", light = picture.src ?: ""),
            isSaved = picture.isSaved,
        )
    }

    fun map(picture: Picture): PicturePreference {
        return PicturePreference(
            id = picture.id.date,
            title = picture.title,
            explanation = picture.explanation,
            copyright = picture.copyright,
            type =  MediaType.Video,
            src = picture.source.light,
            hsrc = picture.source.huge,
            isSaved = picture.isSaved,
            date = null,
        )
    }
}