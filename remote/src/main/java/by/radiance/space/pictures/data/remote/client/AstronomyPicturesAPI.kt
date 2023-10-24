package by.radiance.space.pictures.data.remote.client

import by.radiance.space.pictures.data.remote.entity.NasaAstronomyPicture
import retrofit2.http.GET
import retrofit2.http.Query

interface AstronomyPicturesAPI {

    @GET("/planetary/apod")
    suspend fun get(
        @Query("api_key") apiKey: String,
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("thumbs") thumbnail: Boolean
    ): List<NasaAstronomyPicture>
}