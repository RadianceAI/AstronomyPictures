package by.radiance.space.pictures.domain.repository.remote

import by.radiance.space.pictures.domain.entity.AstronomyPicture
import java.util.*

interface RemoteAstronomyPictureRepository {
    suspend fun get(date: Date, token: String): AstronomyPicture
}