package by.radiance.space.pictures.data.remote

import android.annotation.SuppressLint
import by.radiance.space.pictures.data.remote.client.GetAstronomyPicture
import by.radiance.space.pictures.data.remote.client.NasaAstronomyPictureClient
import by.radiance.space.pictures.domain.entity.AstronomyPicture
import by.radiance.space.pictures.domain.entity.Image
import by.radiance.space.pictures.domain.repository.remote.RemoteAstronomyPictureRepository
import java.text.SimpleDateFormat
import java.util.*

class NasaRemoteAstronomyPictureRepository: RemoteAstronomyPictureRepository {

    override suspend fun get(date: Date, token: String): AstronomyPicture {

        val picturesService = NasaAstronomyPictureClient.client.create(GetAstronomyPicture::class.java)

        val nasaPicture = picturesService.getAstronomyPictures(token, getDate(date), true)

        return AstronomyPicture(
            id = 0,
            title = nasaPicture.title?:"",
            explanation = nasaPicture.explanation?:"",
            copyright = nasaPicture.copyright?:"",
            date = getDate(nasaPicture.date?:""),
            isSaved = false,
            source = Image(
                huge = nasaPicture.hdurl?:"",
                light = nasaPicture.url?:""
            )
        )
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
}