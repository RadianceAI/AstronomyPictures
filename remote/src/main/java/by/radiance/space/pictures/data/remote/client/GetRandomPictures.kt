package by.radiance.space.pictures.data.remote.client

import by.radiance.space.pictures.data.remote.entity.NasaAstronomyPicture
import retrofit2.http.GET
import retrofit2.http.Query

interface GetRandomPictures {

    @GET("/planetary/apod")
    suspend fun get(
            @Query("api_key") apiKey: String,
            @Query("count") count: Int,
            @Query("thumbs") thumbnail: Boolean
    ): List<NasaAstronomyPicture>
}