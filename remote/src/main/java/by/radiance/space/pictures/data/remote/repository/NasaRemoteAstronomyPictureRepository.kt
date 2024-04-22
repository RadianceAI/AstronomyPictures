package by.radiance.space.pictures.data.remote.repository

import by.radiance.space.pictures.data.remote.client.AstronomyPicturesAPI
import by.radiance.space.pictures.data.remote.mapper.PictureMapper
import by.radiance.space.pictures.data.token.NasaTokenRepository
import by.radiance.space.pictures.data.utils.DateUtils
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.source.RemoteSource
import java.util.*

class NasaRemoteAstronomyPictureRepository(
    private val tokenRepository: NasaTokenRepository,
    private val astronomyPictureAPI: AstronomyPicturesAPI,
    private val pictureMapper: PictureMapper,
) : RemoteSource {

    override suspend fun getPictures(startDate: Date, endDate: Date): List<Picture> {
        val rawPictures = astronomyPictureAPI.get(
            apiKey = tokenRepository.get(),
            startDate = DateUtils.getDate(startDate),
            endDate = DateUtils.getDate(endDate),
            thumbnail = true
        )

        return rawPictures.map(pictureMapper::map)
    }
}