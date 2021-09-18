package by.radiance.space.pictures.data.remote.mapper

import by.radiance.space.pictures.data.remote.entity.NasaAstronomyPicture
import by.radiance.space.pictures.data.utils.DateUtils
import by.radiance.space.pictures.data.utils.MediaType
import by.radiance.space.pictures.domain.entity.Id
import by.radiance.space.pictures.domain.entity.Image
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.entity.Video
import kotlin.concurrent.timerTask

class PictureMapper {

    fun map(picture: NasaAstronomyPicture): Picture {
        return Picture(
            id = Id(DateUtils.getDate(picture.date)),
            title = picture.title,
            explanation = picture.explanation,
            copyright = picture.copyright,
            source = when (picture.mediaType) {
                MediaType.Image -> Image(huge = picture.hdurl, light = picture.url)
                MediaType.Video -> Video(thumbnail = picture.url, src = picture.url)
                else -> Image(huge = "", light = "")
            },
            isSaved = false,
            saveDate = null,
        )
    }
}