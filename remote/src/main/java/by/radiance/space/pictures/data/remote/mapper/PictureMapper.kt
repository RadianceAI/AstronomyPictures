package by.radiance.space.pictures.data.remote.mapper

import by.radiance.space.pictures.data.remote.entity.NasaAstronomyPicture
import by.radiance.space.pictures.data.utils.DateUtils
import by.radiance.space.pictures.data.utils.MediaType
import by.radiance.space.pictures.domain.entity.Id
import by.radiance.space.pictures.domain.entity.Image
import by.radiance.space.pictures.domain.entity.Picture
import kotlin.concurrent.timerTask

class PictureMapper {

    fun map(picture: NasaAstronomyPicture, isRandom: Boolean = false): Picture {
        return Picture(
            id = Id(picture.date?:""),
            title = picture.title,
            explanation = picture.explanation,
            copyright = picture.copyright,
            source = Image(huge = picture.hdurl?:"", light = picture.url?:""),
            isSaved = false,
            saveDate = null,
        )
    }
}