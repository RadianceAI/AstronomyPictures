package by.radiance.space.pictures.data.remote.repository

import by.radiance.space.pictures.data.remote.client.GetAstronomyPicture
import by.radiance.space.pictures.data.remote.client.GetRandomPictures
import by.radiance.space.pictures.data.remote.client.NasaAstronomyPictureClient
import by.radiance.space.pictures.data.remote.mapper.PictureMapper
import by.radiance.space.pictures.data.token.NasaTokenRepository
import by.radiance.space.pictures.data.utils.DateUtils
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.repository.RemoteRepository
import java.util.*

class NasaRemoteAstronomyPictureRepository(private val tokenRepository: NasaTokenRepository) :
    RemoteRepository {

    override suspend fun getPicture(date: Date): Picture {
        val picturesService =
            NasaAstronomyPictureClient.client.create(GetAstronomyPicture::class.java)

        val nasaPicture = picturesService.get(
            apiKey = tokenRepository.get(),
            date = DateUtils.getDate(date),
            thumbnail = true
        )

        return PictureMapper().map(nasaPicture)
    }

    override suspend fun getRandomPicture(count: Int): List<Picture> {
        val picturesService =
            NasaAstronomyPictureClient.client.create(GetRandomPictures::class.java)

        val randomPictures = picturesService.get(
            apiKey = tokenRepository.get(),
            count = count,
            thumbnail = true
        )

        return randomPictures.map { nasaPicture ->
            PictureMapper().map(nasaPicture)
        }
    }
}