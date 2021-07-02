package by.radiance.space.pictures.data.remote

import android.annotation.SuppressLint
import by.radiance.space.pictures.data.remote.client.GetAstronomyPicture
import by.radiance.space.pictures.data.remote.client.GetRandomPictures
import by.radiance.space.pictures.data.remote.client.NasaAstronomyPictureClient
import by.radiance.space.pictures.data.remote.entity.NasaAstronomyPicture
import by.radiance.space.pictures.domain.entity.AstronomyPicture
import by.radiance.space.pictures.domain.entity.Image
import by.radiance.space.pictures.domain.entity.PictureId
import by.radiance.space.pictures.domain.repository.remote.RemoteAstronomyPictureRepository
import java.text.SimpleDateFormat
import java.util.*

class NasaRemoteAstronomyPictureRepository: RemoteAstronomyPictureRepository {

    override suspend fun get(date: Date, token: String): AstronomyPicture {
        val picturesService = NasaAstronomyPictureClient.client.create(GetAstronomyPicture::class.java)

        val nasaPicture = picturesService.get(token, getDate(date), true)

        return nasaPicture.toAstronomyPicture()
    }

    override suspend fun getRandomAstronomyPictures(count: Int, token: String): List<AstronomyPicture> {
        val picturesService = NasaAstronomyPictureClient.client.create(GetRandomPictures::class.java)

        val randomPictures = picturesService.get(token, count, true)

        return randomPictures.map { nasaPicture ->
            nasaPicture.toAstronomyPicture()
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDate(date: Date): String {
        val simpleDate = SimpleDateFormat("yyyy-MM-dd")
        return simpleDate.format(date)
    }

    private fun getDate(string: String): Date {
        val simpleDate = SimpleDateFormat("yyyy-MM-dd")
        return simpleDate.parse(string)
    }

    fun NasaAstronomyPicture.toAstronomyPicture(): AstronomyPicture {
        return AstronomyPicture(
                id = PictureId(getDate(this.date?:"")),
                title = this.title?:"",
                explanation = this.explanation?:"",
                copyright = this.copyright?:"",
                isSaved = false,
                source = Image(
                        huge = this.hdurl?:"",
                        light = this.url?:""
                )
        )
    }
}