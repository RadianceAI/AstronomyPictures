package by.radiance.space.pictures.data.remote.client

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object AstronomyPictureClient {
    private const val baseUrl = "https://api.nasa.gov"

    val client: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}