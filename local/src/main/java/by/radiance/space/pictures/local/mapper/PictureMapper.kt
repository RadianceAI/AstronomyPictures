package by.radiance.space.pictures.local.mapper

import by.radiance.space.pictures.domain.entity.Id
import by.radiance.space.pictures.domain.entity.Image
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.utils.DateUtil
import by.radiance.space.pictures.local.entity.AstronomyPicture
import by.radiance.space.pictures.local.entity.SourceType
import java.sql.Date

class PictureMapper {
    fun map(pictures: List<AstronomyPicture>): List<Picture> {
        return pictures.map { map(it) }
    }

    fun map(picture: AstronomyPicture): Picture {
        return Picture(
            id = Id(DateUtil.formatId(picture.id)),
            title = picture.title,
            explanation = picture.explanation,
            copyright = picture.copyright,
            source = Image(huge = picture.hsrc ?: "", light = picture.src ?: ""),
            isSaved = true,
        )
    }

    fun map(picture: Picture): AstronomyPicture {
        val source = picture.source
        return AstronomyPicture(
            id = Date(DateUtil.parseId(picture.id.date).time),
            title = picture.title,
            explanation = picture.explanation,
            copyright = picture.copyright,
            type = SourceType.Image,
            src = source.light,
            hsrc = source.huge,
            saveDate = null,
        )
    }
}